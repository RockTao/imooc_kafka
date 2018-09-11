package com.imooc.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.imooc.kafka.common.MessageEntity;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	@Value("${kafka.consumer.servers}")
	private String servers;
	@Value("${kafka.consumer.enable.auto.commit}")
	private boolean	enableAutoCommit;
	@Value("${kafka.consumer.session.timeout}")
	private String sessionTimeout;
	@Value("${kafka.consumer.auto.commit.interval}")
	private String autoCommitInterval;
	@Value("${kafka.consumer.group.id}")
	private String groupId;
	@Value("${kafka.consumer.auto.offset.reset}")
	private String autoOffsetReset;
	@Value("${kafka.consumer.concurrency}")
	private int concurrency;
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,MessageEntity>> kafkaListenerContainerFactory(){
		
		ConcurrentKafkaListenerContainerFactory<String, MessageEntity> factory = new ConcurrentKafkaListenerContainerFactory<>();
				
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(concurrency);//并发
		factory.getContainerProperties().setPollTimeout(1500);//过期时间
		return factory;
		
		
	}
	
	private ConsumerFactory<String, MessageEntity> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(
				consumerConfigs(),//配置
				new StringDeserializer(),// key的序列化
				new JsonDeserializer(MessageEntity.class));// 对象的反序列化
	}
	
	public Map<String,Object> consumerConfigs(){
		Map<String,Object> props = new HashMap<>();
		
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,servers);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,enableAutoCommit);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,autoCommitInterval);
		
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,sessionTimeout);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,autoOffsetReset);
		
	return props;
	}
	
}
