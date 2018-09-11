package com.imooc.kafka.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.alibaba.fastjson.JSON;
import com.imooc.kafka.common.MessageEntity;

@Component
public class SimpleProducer<T> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("kafkaTemplate")
	private KafkaTemplate<String, MessageEntity> kafkaTemplate;

	public void send(String topic,MessageEntity message) {
		kafkaTemplate.send(topic, message);
	}


	public void send(String topic, String key, MessageEntity entity) {
		logger.info("发送消息入参：{}", entity);
		ProducerRecord<String, MessageEntity> record = new ProducerRecord<>(topic,key,entity);
		long startTime = System.currentTimeMillis();

		ListenableFuture<SendResult<String, MessageEntity>> future =  kafkaTemplate.send(record);
		future.addCallback(new ProducerCallBack(startTime,key,entity));

	}


	//	    public void send(String topic, String key, Object entity) {
	//	        logger.info("发送消息入参：{}", entity);
	//	        ProducerRecord<String, Object> record = new ProducerRecord<>(
	//	                topic,
	//	                key,
	//	                JSON.toJSONString(entity)
	//	        );
	//	
	//	        long startTime = System.currentTimeMillis();
	//	        ListenableFuture<SendResult<String, Object>> future = this.kafkaTemplate.send(record);
	//	        
	//	        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

	//        	public void onFailure(Throwable ex) {
	//                logger.error("消息发送失败：{}", ex);
	//            }
	//
	//            @Override
	//            public void onSuccess(SendResult<String, Object> result) {
	//                long elapsedTime = System.currentTimeMillis() - startTime;
	//
	//                RecordMetadata metadata = result.getRecordMetadata();
	//                StringBuilder record = new StringBuilder(128);
	//                record.append("message(")
	//                        .append("key = ").append(key).append(",")
	//                        .append("message = ").append(entity).append(")")
	//                        .append("send to partition(").append(metadata.partition()).append(")")
	//                        .append("with offset(").append(metadata.offset()).append(")")
	//                        .append("in ").append(elapsedTime).append(" ms");
	//                logger.info("消息发送成功：{}", record.toString());
	//            }
	//        });
	//    }
}