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
 *      DeleteExample.delete(helper,connection,"wjTest2");
 */
public class DeleteExample {

    public static void delete(HBaseHelper helper, Connection connection, String tableName) throws IOException {
        helper.createTable(tableName, 100, "colfam1", "colfam2");

        Table table = connection.getTable(TableName.valueOf(tableName));

        helper.put(tableName,
                new String[] { "row1" },
                new String[] { "colfam1", "colfam2" },
                new String[] { "qual1", "qual1", "qual2", "qual2", "qual3", "qual3" },
                new long[]   { 1, 2, 3, 4, 5, 6 },
                new String[] { "val1", "val1", "val2", "val2", "val3", "val3" });
        System.out.println("Before delete call...");
        helper.dump(tableName, new String[]{"row1"}, null, null);

        Delete delete = new Delete(Bytes.toBytes("row1"));

        delete.setTimestamp(1);

        delete.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1")); // Delete the latest version only in one column.
        delete.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual3"), 3); // Delete specific version in one column.

        delete.addColumns(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1")); // Delete all versions in one column.
        delete.addColumns(Bytes.toBytes("colfam1"), Bytes.toBytes("qual3"), 2); // Delete the given and all older versions in one column.

        delete.addFamily(Bytes.toBytes("colfam1")); // Delete entire family, all columns and versions.
        delete.addFamily(Bytes.toBytes("colfam1"), 3); // Delete the given and all older versions in the entire column family, i.e., from all columns therein.

        table.delete(delete); // Delete the data from the HBase table.

        System.out.println("After delete call...");
        helper.dump(tableName, new String[] { "row1" }, null, null);

        table.close();
    }
}
