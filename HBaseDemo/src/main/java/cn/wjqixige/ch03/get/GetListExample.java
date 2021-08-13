package cn.wjqixige.ch03.get;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Time: 2021-08-13
 * Test Code：
 *      GetListExample.getList(table);
 */
public class GetListExample {

    public static void getList(Table table) throws IOException {

        byte[] cf1 = Bytes.toBytes("colfam1");
        byte[] qf1 = Bytes.toBytes("qual1");
        byte[] qf2 = Bytes.toBytes("qual2");
        byte[] row1 = Bytes.toBytes("row1");
        byte[] row2 = Bytes.toBytes("row2");

        ArrayList<Get> gets = new ArrayList<>();

        Get get1 = new Get(row1);
        get1.addColumn(cf1,qf1);
        gets.add(get1);

        Get get2 = new Get(row2);
        get2.addColumn(cf1,qf1);
        gets.add(get2);

        Get get3 = new Get(row2);
        get3.addColumn(cf1,qf2);
        gets.add(get3);

        Result[] results = table.get(gets);

        System.out.println("First interation...");
        for (Result result : results) {
            String row = Bytes.toString(result.getRow());
            System.out.println("Row: " + row);

            byte[] val = null;

            if (result.containsColumn(cf1,qf1)){
                val = result.getValue(cf1, qf1);
                System.out.println("Value: " + Bytes.toString(val));
            }

            if (result.containsColumn(cf1,qf2)){
                val = result.getValue(cf1,qf2);
                System.out.println("Value: " + Bytes.toString(val));
            }
        }

        System.out.println("Second interation...");
        for (Result result : results) {

            for (Cell cell : result.listCells()){
                // 没有row,报null指针异常
                System.out.println("Row: " + Bytes.toString(cell.getRowArray(),cell.getRowOffset(),cell.getRowLength()));
                System.out.println("Value: " + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }

        System.out.println("Third interation...");
        for (Result result : results) {
            System.out.println("Result: " + result);
        }
    }
}
