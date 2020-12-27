package com.itmayiedu.rabbitmq.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//Fanout ���� ��������ģʽ
@Component
public class FanoutConfig {

	/**
	 * �������Ŷ��������Ϣ
	 */
	public final static String deadQueueName = "dead_queue";
	public final static String deadRoutingKey = "dead_routing_key";
	public final static String deadExchangeName = "dead_exchange";
	/**
	 * ���Ŷ��� ��������ʶ��
	 */
	public static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";
	/**
	 * ���Ŷ��н������󶨼���ʶ��
	 */
	public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

	// �ʼ�����
	private String FANOUT_EMAIL_QUEUE = "fanout_email_queue";

	// ���Ŷ���
	private String FANOUT_SMS_QUEUE = "fanout_sms_queue";
	// fanout ������
	private String EXCHANGE_NAME = "fanoutExchange";

	// 1.�����ʼ�����
	@Bean
	public Queue fanOutEamilQueue() {
		// ����ͨ���а󶨵����Ŷ��н�������
		Map<String, Object> args = new HashMap<>(2);
		args.put(DEAD_LETTER_QUEUE_KEY, deadExchangeName);
		args.put(DEAD_LETTER_ROUTING_KEY, deadRoutingKey);
		Queue queue = new Queue(FANOUT_EMAIL_QUEUE, true, false, false, args);
		return queue;
	}

	// 2.������Ŷ���
	@Bean
	public Queue fanOutSmsQueue() {
		return new Queue(FANOUT_SMS_QUEUE);
	}

	// 2.���彻����
	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange(EXCHANGE_NAME);
	}

	// 3.�����뽻�������ʼ�����
	@Bean
	Binding bindingExchangeEamil(Queue fanOutEamilQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanOutEamilQueue).to(fanoutExchange);
	}

	// 4.�����뽻�����󶨶��Ŷ���
	@Bean
	Binding bindingExchangeSms(Queue fanOutSmsQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanOutSmsQueue).to(fanoutExchange);
	}

	/**
	 * �������Ŷ���
	 * 
	 * @return
	 */
	@Bean
	public Queue deadQueue() {
		Queue queue = new Queue(deadQueueName, true);
		return queue;
	}

	@Bean
	public DirectExchange deadExchange() {
		return new DirectExchange(deadExchangeName);
	}

	@Bean
	public Binding bindingDeadExchange(Queue deadQueue, DirectExchange deadExchange) {
		return BindingBuilder.bind(deadQueue).to(deadExchange).with(deadRoutingKey);
	}

}
