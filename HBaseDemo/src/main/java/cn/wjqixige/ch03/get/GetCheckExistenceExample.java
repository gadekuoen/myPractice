package cn.wjqixige.ch03.get;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2021-08-13
 * Test Code:
 *      GetCheckExistenceExample.getCheckExistence(table);
 */
public class GetCheckExistenceExample {

    public static void getCheckExistence(Table table) throws IOException {

        List<Put> puts = new ArrayList<Put>();

        // 插入数据
        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        puts.add(put1);

        Put put2 = new Put(Bytes.toBytes("row2"));
        put2.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val2"));
        puts.add(put2);

        Put put3 = new Put(Bytes.toBytes("row2"));
        put3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val3"));
        puts.add(put3);

        table.put(puts);

        // 获取数据
        Get get1 = new Get(Bytes.toBytes("row2"));
        get1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));

        // 检查数据是否存在
        get1.setCheckExistenceOnly(true);
        Result result1 = table.get(get1);

        byte[] val = result1.getValue(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));

        System.out.println("Get 1 Exists: " + result1.getExists());
        System.out.println("Get 1 Size: " + result1.size()); // Exists is "true", while no cell was actually returned.
        System.out.println("Get 1 Value: " + Bytes.toString(val));

        Get get2 = new Get(Bytes.toBytes("row2"));
        get2.addFamily(Bytes.toBytes("colfam1"));
        get2.setCheckExistenceOnly(true);
        Result result2 = table.get(get2);

        System.out.println("Get 2 Exists: " + result2.getExists());
        System.out.println("Get 2 Size: " + result2.size());

        //不存在的列
        Get get3 = new Get(Bytes.toBytes("row2"));
        get3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual9999"));
        get3.setCheckExistenceOnly(true);
        Result result3 = table.get(get3);

        System.out.println("Get 3 Exists: " + result3.getExists());
        System.out.println("Get 3 Size: " + result3.size());

        Get get4 = new Get(Bytes.toBytes("row2"));
        get4.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual9999"));
        get4.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));
        get4.setCheckExistenceOnly(true);
        Result result4 = table.get(get4);

        System.out.println("Get 4 Exists: " + result4.getExists()); // Exists is "true" because some data exists.
        System.out.println("Get 4 Size: " + result4.size());
    }
}
