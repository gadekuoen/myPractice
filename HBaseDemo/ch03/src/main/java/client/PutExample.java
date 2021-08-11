package client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;

public class PutExample {

    public static void main(String[] args) throws IOException {

        //1. 创建所需的配置
        Configuration conf = HBaseConfiguration.create();
        conf.addResource(new Path("hbase.hbase-site.dir", "/etc/hbase/conf/hbase-site.xml"));
        conf.addResource(new Path("hbase.core-site.dir", "/etc/hadoop/conf/core-site.xml"));
        //2. 创建一张hbase表
        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("wjTest2");
        helper.createTable("wjTest2","DATA");

        //3.实例化一个新的客户端
        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("wjTest2"));

        //4.指定一行来创建一个Put
        Put put = new Put(Bytes.toBytes("row1"));

        put.addColumn(Bytes.toBytes("DATA"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        put.addColumn(Bytes.toBytes("DATA"), Bytes.toBytes("qual2"), Bytes.toBytes("val2"));

        table.put(put);
        table.close();
        connection.close();
        helper.close();
    }
}
