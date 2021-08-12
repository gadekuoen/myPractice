package cn.wjqixige.ch03.put;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
/**
 * 测试代码：
 * if (Integer.parseInt(args[0]) == 1) {
 *             PutListErrorExample.putListError1(table);
 *         } else if (Integer.parseInt(args[0]) == 2) {
 *             PutListErrorExample.putListError2(table);
 *         } else if (Integer.parseInt(args[0]) == 3){
 *             PutListErrorExample.putListError3(table);
 *         } else {
 *             throw new IllegalArgumentException("Input params ERROR.");
 *         }
 */
public class PutListErrorExample {

    /**
     * 错误的列族名，错误的会报错存入缓冲区，正确的将会存入不受影响
     * 异常：RetriesExhaustedWithDetailsException: Failed 1 action: org.apache.hadoop.hbase.regionserver.
     * NoSuchColumnFamilyException: Column family BOGUS does not exist in region wjTest2
     * @param table
     * @throws IOException
     */
    public static void putListError1(Table table) throws IOException {
        List<Put> puts = new ArrayList<Put>();

        // vv PutListErrorExample1
        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        puts.add(put1);

        Put put2 = new Put(Bytes.toBytes("row2"));
        /*[*/put2.addColumn(Bytes.toBytes("BOGUS"),/*]*/ Bytes.toBytes("qual1"), Bytes.toBytes("val2"));
        puts.add(put2);

        Put put3 = new Put(Bytes.toBytes("row2"));
        put3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val3"));
        puts.add(put3);

        table.put(puts);
    }

    /**
     * 存入空的Put实例，会报错：
     *      Error: java.lang.IllegalArgumentException: No columns to insert
     * @param table
     * @throws IOException
     */
    public static void putListError2(Table table) throws IOException {

        Put put4 = new Put(Bytes.toBytes("row2"));

        try{
            table.put(put4);
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
    }

    /**
     * 多行插入的错误处理方式
     * @param table
     * @throws IOException
     */
    public static void putListError3(Table table) throws IOException {

        List<Put> puts = new ArrayList<Put>();

        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        puts.add(put1);

        Put put2 = new Put(Bytes.toBytes("row2"));
        put2.addColumn(Bytes.toBytes("BOGUS"), Bytes.toBytes("qual1"), Bytes.toBytes("val2"));
        puts.add(put2);

        Put put3 = new Put(Bytes.toBytes("row2"));
        put3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val3"));
        puts.add(put3);

        try {
            table.put(puts);
        } catch (RetriesExhaustedWithDetailsException e) {
            int numErrors = e.getNumExceptions(); // Handle failed operations.
            System.out.println("Number of exceptions: " + numErrors);
            for (int n = 0; n < numErrors; n++) {
                System.out.println("Cause[" + n + "]: " + e.getCause(n));
                System.out.println("Hostname[" + n + "]: " + e.getHostnamePort(n));
                System.out.println("Row[" + n + "]: " + e.getRow(n)); // Gain access to the failed operation.
            }
            System.out.println("Cluster issues: " + e.mayHaveClusterIssues());
            System.out.println("Description: " + e.getExhaustiveDescription());
        }
    }
}
