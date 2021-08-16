package cn.wjqixige.ch03.scan;

import cn.wjqixige.utils.HBaseHelper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

public class ScanTimeoutExample {

    public static void scanTimeout(Configuration conf, Connection connection, String tableName) throws IOException {
        HBaseHelper helper = HBaseHelper.getHelper(conf);

        helper.createTable(tableName,"colfam1", "colfam2");
        System.out.println("Adding row to table...");
        helper.fillTable(tableName,1,10,10,"colfam1","colfam2");

        Table table = connection.getTable(TableName.valueOf(tableName));

        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);

        //获取当前的租约超时时间
        int scannerTimeout = (int) conf.getLong(HConstants.HBASE_CLIENT_SCANNER_TIMEOUT_PERIOD,-1);

        System.out.println("Current (local) lease period: " + scannerTimeout + "ms");
        System.out.println("Sleeping now for " + (scannerTimeout + 5000) + "ms...");

        try {
            //休眠时间比租约超时时间长一点
            Thread.sleep(scannerTimeout + 5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Attempting to iterate over scanner...");

        while(true){
            try {
                Result result = scanner.next();
                if (result == null) break;
                System.out.println(result);
            } catch (Exception e){
                e.printStackTrace();
                break;
            }
        }


        scanner.close();
        table.close();
        helper.close();

    }
}
