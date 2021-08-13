package cn.wjqixige.ch03.batch;

import cn.wjqixige.utils.HBaseHelper;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.client.coprocessor.Batch;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BatchCallbackExample {

    private final static byte[] ROW1 = Bytes.toBytes("row1");
    private final static byte[] ROW2 = Bytes.toBytes("row2");
    private final static byte[] COLFAM1 = Bytes.toBytes("colfam1");
    private final static byte[] COLFAM2 = Bytes.toBytes("colfam2");
    private final static byte[] QUAL1 = Bytes.toBytes("qual1");
    private final static byte[] QUAL2 = Bytes.toBytes("qual2");

    public static void batchCallback(HBaseHelper helper, Connection connection , String tableName) throws IOException {

        helper.createTable(tableName, "colfam1", "colfam2");
        helper.put(tableName,
                new String[] { "row1" },
                new String[] { "colfam1" },
                new String[] { "qual1", "qual2", "qual3" },
                new long[] { 1, 2, 3 },
                new String[] { "val1", "val2", "val3" });

        System.out.println("Before batch call...");
        helper.dump(tableName, new String[] { "row1", "row2" }, null, null);

        Table table = connection.getTable(TableName.valueOf(tableName));

        List<Row> batch = new ArrayList<Row>();

        Put put = new Put(ROW2);
        put.addColumn(COLFAM2, QUAL1, 4, Bytes.toBytes("val5"));
        batch.add(put);

        Get get1 = new Get(ROW1);
        get1.addColumn(COLFAM1, QUAL1);
        batch.add(get1);

        Delete delete = new Delete(ROW1);
        delete.addColumns(COLFAM1, QUAL2);
        batch.add(delete);

        Get get2 = new Get(ROW2);
        get2.addFamily(Bytes.toBytes("BOGUS")); //Add a Get instance that will fail.
        batch.add(get2);

        Object[] results = new Object[batch.size()];
        try {
            table.batchCallback(batch, results, new Batch.Callback<Result>() {
                @Override
                public void update(byte[] region, byte[] row, Result result) {
                    System.out.println("Received callback for row[" +
                            Bytes.toString(row) + "] -> " + result);
                }
            });
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        for (int i = 0; i < results.length; i++) {
            System.out.println("Result[" + i + "]: type = " +
                    results[i].getClass().getSimpleName() + "; " + results[i]);
        }
        System.out.println("After batch call...");
        helper.dump(tableName, new String[]{"row1", "row2"}, null, null);

        table.close();
    }
}
