package com.jeeno.springbootkafka.consumer;

import com.jeeno.springbootkafka.event.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2021/2/1 11:03
 */
@Slf4j
@Component
public class FinalConsumer {

    @KafkaListener(topics = {"final-topic"})
    public void consume(MessageEvent event){
        log.info("#最终消费# {}", event);
    }

}
