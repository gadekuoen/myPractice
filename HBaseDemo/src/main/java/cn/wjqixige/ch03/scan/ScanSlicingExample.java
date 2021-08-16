package cn.wjqixige.ch03.scan;

import cn.wjqixige.utils.HBaseHelper;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.client.metrics.ScanMetrics;

import java.io.IOException;

/**
 * 使用偏移和限制参数进行扫描
 */
public class ScanSlicingExample {

    private static Table table = null;

    public static void scan(int num, int caching, int batch, int offset,
                            int maxResults, int maxResultSize, boolean dump) throws IOException {
        int count = 0;
        Scan scan = new Scan()
                .setCaching(caching)
                .setBatch(batch)
                .setRowOffsetPerColumnFamily(offset)
                .setMaxResultsPerColumnFamily(maxResults)
                .setMaxResultSize(maxResultSize)
                .setScanMetricsEnabled(true);
        ResultScanner scanner = table.getScanner(scan);
        System.out.println("Scan #" + num + " running...");
        for (Result result : scanner) {
            count++;
            if (dump) System.out.println("Result [" + count + "]:" + result);
        }
        scanner.close();
        ScanMetrics metrics = scan.getScanMetrics();
        System.out.println("Caching: " + caching + ", Batch: " + batch +
                ", Offset: " + offset + ", maxResults: " + maxResults +
                ", maxSize: " + maxResultSize + ", Results: " + count +
                ", RPCs: " + metrics.countOfRPCcalls);
    }

    public static void scanSlicing(HBaseHelper helper, Connection connection, String tableName) throws IOException {
        helper.createTable(tableName, "colfam1", "colfam2");
        helper.fillTable(tableName, 1, 10, 10,
                2, true, "colfam1", "colfam2");

        table = connection.getTable(TableName.valueOf(tableName));

        scan(1, 11, 0, 0, 2, -1, true);
        scan(2, 11, 0, 4, 2, -1, true);
        scan(3, 5, 0, 0, 2, -1, false);
        scan(4, 11, 2, 0, 5, -1, true);
        scan(5, 11, -1, -1, -1, 1, false);
        scan(6, 11, -1, -1, -1, 10000, false);

        table.close();
    }
}
