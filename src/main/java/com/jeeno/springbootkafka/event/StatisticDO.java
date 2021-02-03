package com.jeeno.springbootkafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2021/2/1 16:09
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDO {
    private String name;
    private Long count;
}
