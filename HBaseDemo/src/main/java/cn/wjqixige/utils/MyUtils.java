package cn.wjqixige.utils;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class MyUtils {

    public static Put insertData(String rowkey, String family, String qualifier, String value){
        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
        return put;
    }
}
