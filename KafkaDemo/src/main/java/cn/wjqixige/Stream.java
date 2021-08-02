package cn.wjqixige;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.Properties;

public class Stream {

    /**
     * 实现通过StreamAPI实现将数据从test里面读出来，写入到test2中
     * @param args
     */
    //注意：如果程序启动的时候抛出异常，找不到文件夹的路径，需要手动去创建文件夹的路径
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG,"wordcount-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"node01:9092");
        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());

        KStreamBuilder builder = new KStreamBuilder();
        builder.stream("test").mapValues(line -> line.toString().toUpperCase()).to("test2");
        KafkaStreams streams = new KafkaStreams(builder, props);
        streams.start();
    }
}
