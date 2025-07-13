package com.example.kafka.dto.req;

public record KafkaNotificationSendRequest(
	String title,
	String content
) {
}
