package com.example.kafka.kafka.producer;

import java.util.List;

public interface MessageSender<T> {
	void send(T message);

	void send(List<T> messages);
}