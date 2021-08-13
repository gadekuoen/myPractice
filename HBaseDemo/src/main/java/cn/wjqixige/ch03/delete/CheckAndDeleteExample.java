package cn.wjqixige.ch03.delete;

import cn.wjqixige.utils.HBaseHelper;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Time: 2021-08-13
 * Test Code:
 *      CheckAndDeleteExample.checkAndDelete(helper,connection,"wjTest2");
 */
public class CheckAndDeleteExample {

    public static void checkAndDelete(HBaseHelper helper,Connection connection, String tableName) throws IOException {

        helper.createTable(tableName, 100, "colfam1", "colfam2");

        helper.put(tableName,
                new String[] { "row1" },
                new String[] { "colfam1", "colfam2" },
                new String[] { "qual1", "qual2", "qual3" },
                new long[]   { 1, 2, 3 },
                new String[] { "val1", "val2", "val3" });

        System.out.println("Before delete call...");

        helper.dump(tableName, new String[]{ "row1" }, null, null);

        Table table = connection.getTable(TableName.valueOf(tableName));

        Delete delete1 = new Delete(Bytes.toBytes("row1"));
        delete1.addColumns(Bytes.toBytes("colfam1"), Bytes.toBytes("qual3")); // Create a new Delete instance.

        // Check if column does not exist and perform optional delete operation.
        boolean res1 = table.checkAndDelete(Bytes.toBytes("row1"), Bytes.toBytes("colfam2"),
                Bytes.toBytes("qual3"), null, delete1);
        // Print out the result, should be "Delete successful: false".
        System.out.println("Delete 1 successful: " + res1);

        Delete delete2 = new Delete(Bytes.toBytes("row1"));
        delete2.addColumns(Bytes.toBytes("colfam2"), Bytes.toBytes("qual3")); // Delete checked column manually.
        table.delete(delete2);

        boolean res2 = table.checkAndDelete(Bytes.toBytes("row1"),
                Bytes.toBytes("colfam2"), Bytes.toBytes("qual3"), null, delete1); // Attempt to delete same cell again.
        System.out.println("Delete 2 successful: " + res2); // Print out the result, should be "Delete successful: true" since the checked column now is gone.

        Delete delete3 = new Delete(Bytes.toBytes("row2"));
        delete3.addFamily(Bytes.toBytes("colfam1")); // Create yet another Delete instance, but using a different row.

        try{
            boolean res4 = table.checkAndDelete(Bytes.toBytes("row1"),
                    Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), // Try to delete while checking a different row.
                    Bytes.toBytes("val1"), delete3);
            System.out.println("Delete 3 successful: " + res4); // We will not get here as an exception is thrown beforehand!
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("After delete call...");
        helper.dump(tableName, new String[]{"row1"}, null, null);
        table.close();
    }
}
