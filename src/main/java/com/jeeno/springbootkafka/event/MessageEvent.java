package com.jeeno.springbootkafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JEENO
 * @version 0.0.1
 * @date 2021/2/1 10:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageEvent implements Serializable {
    private String id;
    private String name;
}
