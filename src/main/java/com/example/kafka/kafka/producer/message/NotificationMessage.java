package com.example.kafka.kafka.producer.message;

public record NotificationMessage(
	String title,
	String content,
	String timestamp
) {

}
