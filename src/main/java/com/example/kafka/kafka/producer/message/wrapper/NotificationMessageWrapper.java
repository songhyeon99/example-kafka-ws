package com.example.kafka.kafka.producer.message.wrapper;

import java.util.UUID;

import com.example.kafka.kafka.common.BaseMessage;
import com.example.kafka.kafka.producer.message.NotificationMessage;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationMessageWrapper extends BaseMessage<NotificationMessage> {
	public NotificationMessageWrapper(NotificationMessage data) {
		super(UUID.randomUUID().toString(), data);
	}
}
