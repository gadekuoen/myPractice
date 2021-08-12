package cn.wjqixige.ch03.put;

import cn.wjqixige.utils.MyUtils;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 测试代码：
 *  PutListExample.putList(table);
 */
public class PutListExample {

    public static void putList(Table table) throws IOException {

        ArrayList<Put> puts = new ArrayList<>();

        Put put1 = MyUtils.insertData("row1", "colfam1", "qual1", "val1");
        puts.add(put1);

        Put put2 = MyUtils.insertData("row2", "colfam1", "qual1", "val2");
        puts.add(put2);

        Put put3 = MyUtils.insertData("row2", "colfam1", "qual2", "val3");
        puts.add(put3);

        table.put(puts);
    }
}
