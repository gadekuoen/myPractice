package cn.wjqixige.ch03.get;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 测试代码：
 *      GetExample.get(table);
 */
public class GetExample {

    public static void get(Table table) throws IOException {

        Get get = new Get(Bytes.toBytes("row1"));

        get.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));

        Result result = table.get(get);

        byte[] value = result.getValue(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));

        System.out.println("获取的值为： " + Bytes.toString(value));
    }
}
