package com.wpdc.activemqtest;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import javax.jms.Topic;

//@EnableJms
@SpringBootApplication
public class ActivemqtestApplication {
//    @Bean
//    public Queue queue() {
//        return new ActiveMQQueue("sample.queue");
//    }
//
//    @Bean
//    public Topic topic() {
//        return new ActiveMQTopic("sample.topic");
//
//    }
    public static void main(String[] args) {
        SpringApplication.run(ActivemqtestApplication.class, args);
    }
}
