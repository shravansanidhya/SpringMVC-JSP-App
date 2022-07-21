package com.technosmart.util;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technosmart.model.Student;

public class KakfaProducerUtil {

	private String topic = "channel";
	
	public KakfaProducerUtil() {
		super();
	}
	
	public void sendMessage(String key, Student value) throws JsonProcessingException {
		
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(value);
		
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, key, str);
		
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
		
		kafkaProducer.send(producerRecord);
		
		kafkaProducer.close();
	}
	
}
