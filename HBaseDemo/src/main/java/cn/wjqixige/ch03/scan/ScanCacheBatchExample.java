package cn.wjqixige.ch03.scan;

import cn.wjqixige.utils.HBaseHelper;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.client.metrics.ScanMetrics;

import java.io.IOException;

public class ScanCacheBatchExample {

    private static Table table = null;

    public static void scan(int caching, int batch, boolean small) throws IOException{
        int count = 0;
        Scan scan = new Scan()
                .setCaching(caching) //设置缓存行数
                .setBatch(batch) //设置每次调用 next() 时返回的最大值数
                .setSmall(small) //设置本次扫描是否为小扫描
                .setScanMetricsEnabled(true); //启用累计扫描指标
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            count++;
        }
        scanner.close();
        ScanMetrics metrics = scan.getScanMetrics();
        System.out.println("Caching: " + caching + ", Batch: " + batch + ", Small: " + small +
                ", Results: " + count + ", RPCs: " + metrics.countOfRPCcalls);
    }


    public static void scanCacheBatch(HBaseHelper helper, Connection connection, String tableName) throws IOException {
        helper.createTable(tableName,"colfam1","colfam2");
        helper.fillTable(tableName,1,10,10,"colfam1","colfam1");

        table = connection.getTable(TableName.valueOf(tableName));

        scan(1, 1, false);
        scan(1, 0, false);
        scan(1, 0, true);
        scan(200, 1, false);
        scan(200, 0, false);
        scan(200, 0, true);
        scan(2000, 100, false);
        scan(2, 100, false);
        scan(2, 10, false);
        scan(5, 100, false);
        scan(5, 20, false);
        scan(10, 10, false);


        table.close();

    }
}
