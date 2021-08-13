package cn.wjqixige.ch03.put;

import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 测试代码：
 *      PutExample.putOneData(table);
 */
public class PutExample {

    public static void putOneData(Table table) throws IOException {
        Put put = new Put(Bytes.toBytes("row1"));
        put.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        table.put(put);
    }
}
