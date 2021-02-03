package com.jeeno.springbootkafka.producer;

import com.jeeno.springbootkafka.event.MessageEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2021/2/1 10:38
 */
@Component
public class MessageEventProducer {

    @Resource
    KafkaTemplate<String, MessageEvent> kafkaTemplate;

    @Scheduled(fixedRate = 2000)
    public void send(){
        kafkaTemplate.send("process-topic","name",
                new MessageEvent(UUID.randomUUID().toString(), "jeeno"));
    }

    @Scheduled(fixedRate = 3000)
    public void send2(){
        kafkaTemplate.send("process-topic","name",
                new MessageEvent(UUID.randomUUID().toString(), "tom"));
    }
}
