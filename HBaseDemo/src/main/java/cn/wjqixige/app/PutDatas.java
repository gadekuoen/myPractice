package cn.wjqixige.app;


import cn.wjqixige.app.domain.MeasurementElement;
import cn.wjqixige.app.domain.MpYCDataMsg;
import cn.wjqixige.app.enumeration.SourceSystem;
import cn.wjqixige.app.enumeration.SystemPrefix;
import cn.wjqixige.app.utils.DateUtil;
import cn.wjqixige.utils.HBaseHelper;
import com.alibaba.fastjson.JSON;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


import java.io.IOException;
import java.util.ArrayList;

// args[0] tableName,args[1] nums
public class PutDatas {
    private final static String[] tagArr = {"Ua","Ub","Uc","Ia","Ib","Ic","P","Q","CT","PT"};

    public static void main(String[] args) throws IOException, InterruptedException {

        Configuration conf = HBaseConfiguration.create();
        conf.addResource(new Path("hbase.hbase-site.dir", "/etc/hbase/conf/hbase-site.xml"));
        conf.addResource(new Path("hbase.core-site.dir", "/etc/hadoop/conf/core-site.xml"));

        HBaseHelper helper = HBaseHelper.getHelper(conf);

        //删除存在的hbase表并创建
        if (helper.existsTable(args[0])){
            helper.dropTable(args[0]);
        }
        helper.createTable(args[0],"DATA");

        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf(args[0]));

        //插入数据
        ArrayList<Put> puts = new ArrayList<>();

        for (int i = 0; i < Integer.parseInt(args[1]); i++) {
            long timestamp = System.currentTimeMillis();
            Thread.sleep(1000);
            //数据冻结时间
            String freezeTime = DateUtil.getCurrentTimeStr(timestamp);
            String freezeTimeDesc = DateUtil.getDescLongTime(freezeTime);

            //列键时间RKSJ
            String longTime = DateUtil.getCurrentDescLongTime(timestamp);

            System.out.println("第" + i + "条数据时间点：" + timestamp + "--" + freezeTime + "--" + freezeTimeDesc);
//
            String mpid = SystemPrefix.YONGCAI.getCode() + DateUtil.autoGenericCode("139396",60);
            String rowkey = mpid + "|" + freezeTimeDesc;

            //Value
            String valStr = parseJSON(mpid);

            Put put = new Put(Bytes.toBytes(rowkey));
            put.addColumn(Bytes.toBytes("DATA"),Bytes.toBytes(longTime),Bytes.toBytes(valStr));
            puts.add(put);
        }

        table.put(puts);

        //关闭资源
        table.close();
        connection.close();
        helper.close();

    }

    private static String parseJSON(String mpid){

        float randomNum = Float.parseFloat(Math.random() * 100 + "");

        MpYCDataMsg dataMsg = new MpYCDataMsg();
        dataMsg.setRKSJ(DateUtil.getCurrentTimeStr());
        dataMsg.setFDT(DateUtil.getCurrentTimeStr());
        dataMsg.setMPID(mpid);
        dataMsg.setSSID(SourceSystem.EDAS.getCode());

        ArrayList<MeasurementElement> list = new ArrayList<>();
        for (String s : tagArr) {
            MeasurementElement element = new MeasurementElement();
            element.setVAL(String.format("%.2f", randomNum));
            element.setQ("0");
            element.setTAG(s);
            element.setDT(DateUtil.getCurrentTimeStr());
            list.add(element);
        }
        dataMsg.setMDATA(list);

        return JSON.toJSONString(dataMsg);
    }

}