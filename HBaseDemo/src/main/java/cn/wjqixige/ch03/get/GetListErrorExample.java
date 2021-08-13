package cn.wjqixige.ch03.get;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2021-08-13
 * Test Code:
 *      GetListErrorExample.getListError(table);
 */
public class GetListErrorExample {

    public static void getListError(Table table) throws IOException {
        byte[] cf1 = Bytes.toBytes("colfam1");
        byte[] qf1 = Bytes.toBytes("qual1");
        byte[] qf2 = Bytes.toBytes("qual2");
        byte[] row1 = Bytes.toBytes("row1");
        byte[] row2 = Bytes.toBytes("row2");

        List<Get> gets = new ArrayList<Get>();

        Get get1 = new Get(row1);
        get1.addColumn(cf1, qf1);
        gets.add(get1);

        Get get2 = new Get(row2);
        get2.addColumn(cf1, qf1); //Add the Get instances to the list.
        gets.add(get2);

        Get get3 = new Get(row2);
        get3.addColumn(cf1, qf2);
        gets.add(get3);

        Get get4 = new Get(row2);
        /*[*/get4.addColumn(Bytes.toBytes("BOGUS"),/*]*/ qf2);
        gets.add(get4); // Add the bogus column family get.

        Result[] results = table.get(gets); // An exception is thrown and the process is aborted.

        System.out.println("Result count: " + results.length); //This line will never reached!
    }
}
