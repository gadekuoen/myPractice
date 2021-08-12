package cn.wjqixige;

import cn.wjqixige.ch03.get.GetExample;
import cn.wjqixige.utils.HBaseHelper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;


import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("HBaseDemo程序测试入口！");

        //连接hbase客户端，并删除创建新表
        Configuration conf = HBaseConfiguration.create();
        conf.addResource(new Path("hbase.hbase-site.dir", "/etc/hbase/conf/hbase-site.xml"));
        conf.addResource(new Path("hbase.core-site.dir", "/etc/hadoop/conf/core-site.xml"));

        HBaseHelper helper = HBaseHelper.getHelper(conf);
//        helper.dropTable("wjTest2");
        if (!helper.existsTable("wjTest2")){
            helper.createTable("wjTest2","colfam1");
        }

        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("wjTest2"));

        //代码测试区域 start



        //代码测试区域 end

        //关闭连接
        helper.close();
        table.close();
        connection.close();
    }
}
