package com.jeeno.springbootkafka.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2021/2/1 10:36
 */
@Component
public class MessageEventTopic {

    @Bean
    NewTopic topic(){
        //使用NewTopic定义确认所需用的Topic
        return new NewTopic("custom-topic",1 ,(short) 1);
    }

}
