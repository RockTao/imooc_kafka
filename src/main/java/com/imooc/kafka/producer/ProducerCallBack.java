package com.imooc.kafka.producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.alibaba.fastjson.JSONObject;
import com.imooc.kafka.common.MessageEntity;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ProducerCallBack implements ListenableFutureCallback< SendResult<String, MessageEntity>> {

	
	
	private final long startTime;//时间
	private final String key;//
	private final MessageEntity message;//对象
	public ProducerCallBack(long startTime, String key, MessageEntity message) {
		super();
		this.startTime = startTime;
		this.key = key;
		this.message = message;
	}
	@Override
	public void onSuccess(@Nullable SendResult<String, MessageEntity> result) {
		if(result == null) {
			return ;
		}
		long elapsedTime = System.currentTimeMillis() -startTime;
		RecordMetadata metadata = result.getRecordMetadata();
		if(metadata != null) {
			StringBuilder record = new StringBuilder();
			record.append("message(").append("key=").append(key).append(",")
			.append("message = ").append(JSONObject.toJSONString(message)).append("sent to partition(")
			.append(metadata.partition()).append("in ").append(elapsedTime).append(" ms");
			log.info(record.toString());
		}
	}
	@Override
	public void onFailure(Throwable ex) {
		ex.printStackTrace();
	}
	
}
