package com.lanou.config;

import groovy.transform.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: springboot01
 * @description:
 * @author: hutingrong
 * @create: 2019-07-21 13:41
 */
@Component
@ConfigurationProperties(prefix = "Time")
@EqualsAndHashCode(callSuper = false)
public class TimeConfig {
    private String corn1;
    private String corn2;

    public String getCorn1() {
        return corn1;
    }

    public void setCorn1(String corn1) {
        this.corn1 = corn1;
    }

    public String getCorn2() {
        return corn2;
    }

    public void setCorn2(String corn2) {
        this.corn2 = corn2;
    }
}
