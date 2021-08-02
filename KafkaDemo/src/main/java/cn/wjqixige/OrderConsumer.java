package cn.wjqixige;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class OrderConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers","node01:9092");
        props.put("group.id","test");
        //消费者自动提交offset值
        props.put("enable.auto.commit","true");
        props.put("auto.commit.interval.ms","1000");

        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);

        kafkaConsumer.subscribe(Arrays.asList("order"));
        while(true){
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            for (ConsumerRecord<String,String> record : consumerRecords){
                System.out.println("消费的数据为： " + record.value());
            }
        }
    }
}
