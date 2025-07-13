package com.example.kafka.kafka.listener.message;

public record NotificationMessage(
	String title,
	String content,
	String timestamp
) {

}