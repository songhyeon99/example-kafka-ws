package com.example.kafka.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.kafka.dto.req.KafkaNotificationSendRequest;
import com.example.kafka.kafka.producer.message.NotificationMessage;
import com.example.kafka.kafka.producer.sender.NotificationSyncSender;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaNotificationService {
	private final NotificationSyncSender notificationSyncSender;

	public void send(KafkaNotificationSendRequest request) {
		NotificationMessage message = new NotificationMessage(request.title(), request.content(),
			LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		notificationSyncSender.send(message);
	}
}
