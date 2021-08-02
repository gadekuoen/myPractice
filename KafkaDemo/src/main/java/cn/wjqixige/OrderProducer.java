package cn.wjqixige;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class OrderProducer {

    public static void main(String[] args) throws InterruptedException {
        //1. 连接集群，通过配置文件的方式
        Properties props = new Properties();
        props.put("bootstrap.servers","node01:9092");
        props.put("acks","all");
        props.put("retries",0);
        props.put("batch.size",16384);
        props.put("linger.ms",1);
        props.put("buffer.memeory",33554432);
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);
        for (int i = 0; i < 1000; i++) {
            //发送数据，需要一个producerRecord对象，最少参数 String topic
            kafkaProducer.send(new ProducerRecord<String, String>("test", "wujiang zhuan daxie " + i));
            Thread.sleep(100);
        }
    }
}
