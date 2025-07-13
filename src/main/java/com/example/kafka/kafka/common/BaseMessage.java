package com.example.kafka.kafka.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class BaseMessage<T> {
	String transactionId;
	T data;

	public BaseMessage(String transactionId, T data) {
		this.transactionId = transactionId;
		this.data = data;
	}
}

