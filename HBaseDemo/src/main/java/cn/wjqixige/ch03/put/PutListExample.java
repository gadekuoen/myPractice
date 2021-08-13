package cn.wjqixige.ch03.put;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 测试代码：
 *  PutListExample.putList(table);
 */
public class PutListExample {

    public static void putList(Table table) throws IOException {

        ArrayList<Put> puts = new ArrayList<>();

        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        puts.add(put1);

        Put put2 = new Put(Bytes.toBytes("row2"));
        put2.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val2"));
        puts.add(put2);

        Put put3 = new Put(Bytes.toBytes("row2"));
        put3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val3"));
        puts.add(put3);

        table.put(puts);
    }
}
