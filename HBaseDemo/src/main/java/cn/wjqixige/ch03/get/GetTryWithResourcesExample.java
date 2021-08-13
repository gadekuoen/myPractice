package cn.wjqixige.ch03.get;

import cn.wjqixige.utils.HBaseHelper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 使用 Java 7 构造从 HBase 检索数据
 * Time: 2021-08-13
 * Test Code:
 *      GetTryWithResourcesExample.getTryWithResources(helper,conf);
 */
public class GetTryWithResourcesExample {

    public static void getTryWithResources(HBaseHelper helper,Configuration conf) throws IOException {

//        helper.dropTable("wjTest2");
        if (!helper.existsTable("wjTest2")){
            helper.createTable("wjTest2","colfam1");
        }

        //不需要显式关闭，Java 将处理 AutoClosable。
        try (
            Connection connection = ConnectionFactory.createConnection(conf);
            Table table = connection.getTable(TableName.valueOf("wjTest2"));
        ) {
            Get get = new Get(Bytes.toBytes("row1"));
            get.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));
            Result result = table.get(get);
            byte[] val = result.getValue(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));
            System.out.println("Value: " + Bytes.toString(val));
        }

        helper.close();
    }
}
