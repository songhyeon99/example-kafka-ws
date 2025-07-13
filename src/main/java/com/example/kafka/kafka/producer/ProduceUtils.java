package com.example.kafka.kafka.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProduceUtils {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public void send(String topic, Object data) {
		ObjectMapper kafkaMapper = new ObjectMapper();
		kafkaMapper.registerModule(new JavaTimeModule());

		try {
			String value = kafkaMapper.writeValueAsString(data);
			CompletableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send(topic, value);

			sendResult.whenComplete((result, throwable) -> {
				if (throwable != null) {
					log.error("Failed to send message: {} , {}", topic, data, throwable);
				} else {
					log.info("Sent message: {} , {}", topic, data);
				}
			});
		} catch (JsonProcessingException e) {
			log.error("Failed to parse message: {}", data, e);
		}
	}
}