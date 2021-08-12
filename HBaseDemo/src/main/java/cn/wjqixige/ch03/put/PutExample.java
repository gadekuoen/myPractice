package cn.wjqixige.ch03.put;

import cn.wjqixige.utils.MyUtils;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

/**
 * 测试代码：
 *      PutExample.putOneData(table);
 */
public class PutExample {

    public static void putOneData(Table table) throws IOException {
        Put put = MyUtils.insertData("row1", "colfam1", "qual1", "val1");
        table.put(put);
    }
}
