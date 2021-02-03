package com.jeeno.springbootkafka.consumer;

import com.jeeno.springbootkafka.event.StatisticDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2021/2/1 11:31
 */
@Slf4j
@Component
public class NameStatisticConsumer {

    @KafkaListener(topics = {"statistic-topic"})
    public String consume(StatisticDO nums){
        log.info("#姓名统计# {}, {}", nums.getName(), nums.getCount());
        return nums.toString();
    }

}
