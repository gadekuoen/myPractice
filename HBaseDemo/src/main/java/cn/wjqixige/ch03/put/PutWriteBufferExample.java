package cn.wjqixige.ch03.put;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 测试代码：
 *      if (Integer.parseInt(args[0]) == 1){
 *             PutWriteBufferExample.putWriteBuffer1(table,connection,"wjTest2");
 *         } else if (Integer.parseInt(args[0]) == 2){
 *             PutWriteBufferExample.putWriteBuffer2(table,connection,"wjTest2");
 *         } else {
 *             throw new IllegalArgumentIOException("arg[0] Error!");
 *         }
 */
public class PutWriteBufferExample {

    public static void putWriteBuffer1(Table table, Connection connection, String tableName) throws IOException {

        //获取表的一个mutator实例
        BufferedMutator mutator = connection.getBufferedMutator(TableName.valueOf(tableName));

        //插入一条测试数据数据
        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        mutator.mutate(put1); // Store some rows with columns into HBase.

        Put put2 = new Put(Bytes.toBytes("row1"));
        put2.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val2"));
        mutator.mutate(put2);

        Put put3 = new Put(Bytes.toBytes("row2"));
        put3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val3"));
        mutator.mutate(put3);

        //查看mutator刷新前后
        Get row1 = new Get(Bytes.toBytes("row1"));
        Result res1 = table.get(row1);
        System.out.println("Result: " + res1);

        mutator.flush();

        Result res2 = table.get(row1);
        System.out.println("Result: " + res2);

        mutator.close();
    }

    public static void putWriteBuffer2(Table table, Connection connection, String tableName) throws IOException {

        //获取表的一个mutator实例
        BufferedMutator mutator = connection.getBufferedMutator(TableName.valueOf(tableName));

        ArrayList<Mutation> mutations= new ArrayList<>();

        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        mutations.add(put1);

        Put put2 = new Put(Bytes.toBytes("row2"));
        put2.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val2"));
        mutations.add(put2);

        Put put3 = new Put(Bytes.toBytes("row3"));
        put3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val3"));
        mutations.add(put3);

        mutator.mutate(mutations);

        Get get = new Get(Bytes.toBytes("row1"));
        Result res3 = table.get(get);
        System.out.println("Result : " + res3);

        mutator.flush();

        Result res4 = table.get(get);
        System.out.println("Result : " + res4);

        mutator.close();
    }
}
