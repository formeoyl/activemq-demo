package com.wpdc.activemqtest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

@Controller
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

//    @Autowired
//    private Queue queue;
//    @Autowired
//    private Topic topic;

    @Resource(name = "queue1")
    private Destination queue;

    @Resource(name = "topic1")
    private Destination topic;

    @ResponseBody
    @RequestMapping("/sendMsg")
    public void send(String msg) {
        for(int i=0;i<10;i++){
            this .jmsMessagingTemplate.convertAndSend(this.queue, "queue"+i);
            this.jmsMessagingTemplate.convertAndSend(this.topic, "topic"+i);
        }
    }

    @ResponseBody
    @RequestMapping("/product")
    public Object product(String message) {
        this .jmsMessagingTemplate.convertAndSend(this.queue, message);
        return "200";
    }
}
