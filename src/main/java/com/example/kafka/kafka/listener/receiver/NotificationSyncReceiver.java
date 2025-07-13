package com.example.kafka.kafka.listener.receiver;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.kafka.kafka.listener.MessageReceiver;
import com.example.kafka.kafka.listener.message.NotificationMessage;
import com.example.kafka.kafka.listener.message.wrapper.NotificationMessageWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationSyncReceiver implements MessageReceiver<NotificationMessageWrapper> {
	private final ObjectMapper objectMapper;

	@KafkaListener(
		topics = {"TOAST"},
		properties = {
			ConsumerConfig.MAX_POLL_RECORDS_CONFIG + "=1",
			ConsumerConfig.AUTO_OFFSET_RESET_CONFIG + "=earliest"
		}
	)

	@Override
	public void onReceive(NotificationMessageWrapper message) {
		log.info("CompanySyncListener run: {}", message);
		NotificationMessage data = message.getData();
	}

	@Override
	public void onRaw(String raw) {
		try {
			log.info("Received message: {}", raw);
			NotificationMessageWrapper notificationMessage = objectMapper.readValue(raw,
				NotificationMessageWrapper.class);
			log.info("Received message: {}", notificationMessage);
			onReceive(notificationMessage);
		} catch (JsonProcessingException e) {
			log.error("Failed to parse message: {}", raw, e);
			throw new IllegalArgumentException(e);
		}
	}
}
