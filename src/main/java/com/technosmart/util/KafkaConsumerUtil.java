package com.technosmart.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technosmart.model.Student;
public class KafkaConsumerUtil {

	public KafkaConsumerUtil() {
		
	}
	
	public Student recieveMessage() throws JsonParseException, JsonMappingException, IOException {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("group.id", "kafka-group-1");
		properties.put("auto.offset.reset", "earliest");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		
		consumer.subscribe(Arrays.asList("channel"));
		Student student = null;
		
		try {
				ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
				for(ConsumerRecord<String, String> consumerRecord : consumerRecords) {
					
					System.out.println("key: "+consumerRecord.key());
					System.out.println("value: "+consumerRecord.value());
					System.out.println("topic: "+consumerRecord.topic());
					student = new ObjectMapper().readValue(consumerRecord.value(), Student.class);
					System.out.println("partition: "+consumerRecord.partition());
			}
		} finally {
			consumer.close();
		}
		return student;
	}
	
}
