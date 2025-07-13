package com.example.kafka.kafka.listener;

public interface MessageReceiver<T> {
	void onReceive(T message);

	void onRaw(String raw);
}
