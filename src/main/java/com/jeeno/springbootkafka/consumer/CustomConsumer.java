package com.jeeno.springbootkafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2021/2/1 10:45
 */
@Slf4j
@Component
public class CustomConsumer {

    @KafkaListener(topics = {"custom-topic"})
    public void consume(String message){
        log.info("#通用处理# {}", message);
    }

}
