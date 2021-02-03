package com.jeeno.springbootkafka.streams;

import com.jeeno.springbootkafka.event.MessageEvent;
import com.jeeno.springbootkafka.event.StatisticDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.SessionWindows;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collections;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2021/2/1 11:01
 */
@Slf4j
@Component
public class NameProcessStream {

    @Resource
    private StreamsBuilder streamsBuilder;

    @Bean
    public KStream<String, MessageEvent> kStream(){
        KStream<String, MessageEvent> stream = streamsBuilder.stream("process-topic");
        stream.map((key, value) -> {
            value.setName(value.getName().toUpperCase());
            return new KeyValue<>(key,value);
        })
                .peek((key, value) -> log.info("# KStream #" + key + " : "+ value))
                .to("final-topic");
        return stream;
    }

//    @Bean
//    public KTable<String, Long> nameStatistics() {
//        KTable<String, MessageEvent> table = streamsBuilder.table("final-topic");
//        KTable<String, Long> nameCounts = table.toStream()
//                .flatMapValues(message -> Collections.singleton(message.getName().toUpperCase()))
//                .peek((key, value) -> log.info("# statistic #" + key + " : " + value))
//                .groupBy((key, word)->word)
//                .count();
//        // 为了兼容设置的默认json反序列化，自行做对象封装
//        nameCounts.toStream().map((key, value) -> new KeyValue<>(key, new StatisticDO(key, value)))
//                .to("statistic-topic");
//        return nameCounts;
//    }

//    /**
//     * 每30s执行一次流处理（通过commit.interval.ms全局设置）
//     * @return
//     */
//    @Bean
//    public KTable<String, MessageEvent> nameStatistics() {
//        KTable<String, MessageEvent> table = streamsBuilder.table("final-topic");
//        table.toStream()
//                .flatMapValues(message -> Collections.singleton(message.getName()))
//                .groupBy((key, word) -> word)
//                .windowedBy(TimeWindows.of(Duration.ofSeconds(5L)))
//                .count()
//                .toStream()
//                .peek((x,y) -> log.info("# nameStatistics #  x:{}, y:{}", x, y));
//        return table;
//    }

    /**
     * 每30s执行一次流处理 （通过commit.interval.ms全局设置）以消息到来的N s时间为一个会话窗口
     * @return
     */
    @Bean
    public KTable<String, MessageEvent> sessionStatistics() {
        KTable<String, MessageEvent> table = streamsBuilder.table("final-topic");
        table.toStream()
                .flatMapValues(message -> Collections.singleton(message.getName()))
                .groupBy((key, word) -> word)
                .windowedBy(SessionWindows.with(Duration.ofSeconds(2L)))
                .count(Named.as("session-window"))
                .toStream()
                .peek((x,y) -> log.info("# sessionStatistics #  x:{}, y:{}", x, y));
        log.info("{}", streamsBuilder.build().describe());
        return table;
    }

}
