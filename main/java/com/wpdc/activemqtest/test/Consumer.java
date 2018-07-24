package com.wpdc.activemqtest.test;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class Consumer {
	
//	@JmsListener(destination = "queue-1")
//	public void receiveQueue(String text) {
//		System.out.println("1-queue-消费："+text);
//	}
//
//	@JmsListener(destination = "queue-1")
//	public void receiveQueue2(String text) {
//		System.out.println("2-queue-消费："+text);
//	}

	@JmsListener(destination = "topic-1", containerFactory="topicListenerContainer")
	public void receiveTopic(String text) {
		System.out.println("1-topic-消费"+text);

	}

	@JmsListener(destination = "topic-1", containerFactory="topicListenerContainer")
	public void receiveTopic2(String text) {
		System.out.println("2-topic-消费"+text);

	}
}