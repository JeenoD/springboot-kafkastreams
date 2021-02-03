package com.jeeno.springbootkafka.consumer;

import com.jeeno.springbootkafka.event.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2021/2/1 10:43
 */
@Slf4j
@Component
public class MessageEventConsumer {

    @KafkaListener(topics = {"process-topic"})
    @SendTo("custom-topic")
    public String consume(MessageEvent record){
        log.info("#处理器1# {}", record);
        return record.toString();
    }

}
