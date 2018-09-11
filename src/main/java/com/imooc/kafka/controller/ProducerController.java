package com.imooc.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.imooc.kafka.common.ErrorCode;
import com.imooc.kafka.common.MessageEntity;
import com.imooc.kafka.common.Response;
import com.imooc.kafka.producer.SimpleProducer;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SimpleProducer simpleProducer;

    @Value("${kafka.topic.default}")
    private String topic;

    private static final String KEY = "key";
    
    @RequestMapping(value="/hello",method = RequestMethod.GET, produces = { "application/JSON"  })
    public Response sendKafka() {
    	 return new Response(ErrorCode.SUCCESS,"测试消息------发送成功");
    }
 

    @RequestMapping(value="/send",method = RequestMethod.POST, produces = { "application/JSON"  })
    public Response sendKafka(@RequestBody MessageEntity message) {
        try {
            logger.info("kafka的消息：{}", JSON.toJSONString(message));
            this.simpleProducer.send(topic, KEY, message);
            logger.info("kafka消息发送成功！");
            return new Response(ErrorCode.SUCCESS,"kafka消息发送成功");
        } catch (Exception ex) {
            logger.error("kafka消息发送失败：", ex);
            return new Response(ErrorCode.EXCEPTION,"kafka消息发送失败");
        }
    }
}