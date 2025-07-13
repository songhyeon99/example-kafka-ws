package com.example.kafka.kafka.producer.sender;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.kafka.kafka.producer.MessageSender;
import com.example.kafka.kafka.producer.ProduceUtils;
import com.example.kafka.kafka.producer.message.NotificationMessage;
import com.example.kafka.kafka.producer.message.wrapper.NotificationMessageWrapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationSyncSender implements MessageSender<NotificationMessage> {
	private final String TOPIC = "TOAST";
	private final ProduceUtils produceUtils;

	@Override
	public void send(NotificationMessage message) {
		NotificationMessageWrapper wrapper = new NotificationMessageWrapper(message);
		produceUtils.send(TOPIC, wrapper);
	}

	@Override
	public void send(List<NotificationMessage> messages) {
		messages.forEach(this::send);
	}
}
