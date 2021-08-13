package cn.wjqixige.ch03.delete;

import cn.wjqixige.utils.HBaseHelper;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2021-08-13
 * Test Code:
 *      DeleteListExample.deleteList(helper,table,"wjTest2");
 */
public class DeleteListExample {

    public static void deleteList(HBaseHelper helper, Connection connection, String tableName) throws IOException {

        helper.createTable(tableName, 100, "colfam1", "colfam2");

        Table table = connection.getTable(TableName.valueOf(tableName));

        helper.put(tableName,
                new String[] { "row1" },
                new String[] { "colfam1", "colfam2" },
                new String[] { "qual1", "qual1", "qual2", "qual2", "qual3", "qual3" },
                new long[]   { 1, 2, 3, 4, 5, 6 },
                new String[] { "val1", "val2", "val3", "val4", "val5", "val6" });
        helper.put(tableName,
                new String[] { "row2" },
                new String[] { "colfam1", "colfam2" },
                new String[] { "qual1", "qual1", "qual2", "qual2", "qual3", "qual3" },
                new long[]   { 1, 2, 3, 4, 5, 6 },
                new String[] { "val1", "val2", "val3", "val4", "val5", "val6" });
        helper.put(tableName,
                new String[] { "row3" },
                new String[] { "colfam1", "colfam2" },
                new String[] { "qual1", "qual1", "qual2", "qual2", "qual3", "qual3" },
                new long[]   { 1, 2, 3, 4, 5, 6 },
                new String[] { "val1", "val2", "val3", "val4", "val5", "val6" });

        System.out.println("Before delete call...");

        helper.dump(tableName, new String[]{ "row1", "row2", "row3" }, null, null);


        List<Delete> deletes = new ArrayList<Delete>(); // Create a list that holds the Delete instances.

        Delete delete1 = new Delete(Bytes.toBytes("row1"));
        delete1.setTimestamp(4); // Set timestamp for row deletes.
        deletes.add(delete1);

        Delete delete2 = new Delete(Bytes.toBytes("row2"));
        delete2.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1")); // Delete the latest version only in one column.
        delete2.addColumns(Bytes.toBytes("colfam2"), Bytes.toBytes("qual3"), 5); // Delete the given and all older versions in another column.
        deletes.add(delete2);

        Delete delete3 = new Delete(Bytes.toBytes("row3"));
        delete3.addFamily(Bytes.toBytes("colfam1")); // Delete entire family, all columns and versions.
        delete3.addFamily(Bytes.toBytes("colfam2"), 3); // Delete the given and all older versions in the entire column family, i.e., from all columns therein.
        deletes.add(delete3);

        table.delete(deletes); //Delete the data from multiple rows the HBase table.

        System.out.println("After delete call...");

        helper.dump(tableName, new String[]{ "row1", "row2", "row3" }, null, null);

        table.close();
    }
}
