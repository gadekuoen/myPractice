package cn.wjqixige.ch03.put;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 测试代码：
 *      PutIdenticalExample.putIdentical(table);
 */
public class PutIdenticalExample {

    public static void putIdentical(Table table) throws IOException {
        Put put = new Put(Bytes.toBytes("row1"));
        put.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val2"));
        // 添加相同的列，不同的值，则最后添加的被应用
        put.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        table.put(put);

        Get get = new Get(Bytes.toBytes("row1"));
        Result result = table.get(get);
        System.out.println("Result: " + result + ", Value: " +
                Bytes.toString(result.getValue(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"))));
    }
}
