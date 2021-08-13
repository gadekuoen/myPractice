package cn.wjqixige.ch03.scan;

import cn.wjqixige.utils.HBaseHelper;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;


public class ScanExample {

    public static void scan(HBaseHelper helper, Connection connection, String tableName) throws IOException {

        helper.createTable(tableName, "colfam1", "colfam2");
        System.out.println("Adding rows to table...");

        // Tip: Remove comment below to enable padding, adjust start and stop
        // row, as well as columns below to match.
        helper.fillTable(tableName, 1, 100, 100, /* 3, false, */ "colfam1", "colfam2");

        Table table = connection.getTable(TableName.valueOf(tableName));

        System.out.println("Scanning table #1...");
        // vv ScanExample
        Scan scan1 = new Scan();
        ResultScanner scanner1 = table.getScanner(scan1);
        for (Result res : scanner1) {
            System.out.println(res);
        }
        scanner1.close();

        System.out.println("Scanning table #2...");
        Scan scan2 = new Scan();
        scan2.addFamily(Bytes.toBytes("colfam1"));
        ResultScanner scanner2 = table.getScanner(scan2);
        for (Result res : scanner2) {
            System.out.println(res);
        }
        scanner2.close();

        System.out.println("Scanning table #3...");
        Scan scan3 = new Scan();
        scan3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("col-5")).
                addColumn(Bytes.toBytes("colfam2"), Bytes.toBytes("col-33")).
                setStartRow(Bytes.toBytes("row-10")).
                setStopRow(Bytes.toBytes("row-20"));
        ResultScanner scanner3 = table.getScanner(scan3);
        for (Result res : scanner3) {
            System.out.println(res);
        }
        scanner3.close();

        System.out.println("Scanning table #4...");
        Scan scan4 = new Scan();
        scan4.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("col-5")).
                setStartRow(Bytes.toBytes("row-10")).
                setStopRow(Bytes.toBytes("row-20"));
        ResultScanner scanner4 = table.getScanner(scan4);
        for (Result res : scanner4) {
            System.out.println(res);
        }
        scanner4.close();

        System.out.println("Scanning table #5...");
        Scan scan5 = new Scan();
        // When using padding above, use "col-005", and "row-020", "row-010".
        scan5.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("col-5")).
                setStartRow(Bytes.toBytes("row-20")).
                setStopRow(Bytes.toBytes("row-10")).
                setReversed(true); // co ScanExample-8-Build One column scan that runs in reverse.
        ResultScanner scanner5 = table.getScanner(scan5);
        for (Result res : scanner5) {
            System.out.println(res);
        }
        scanner5.close();

        table.close();
    }
}
