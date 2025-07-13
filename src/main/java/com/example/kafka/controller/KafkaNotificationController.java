package com.example.kafka.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.kafka.dto.req.KafkaNotificationSendRequest;
import com.example.kafka.service.KafkaNotificationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class KafkaNotificationController {
	private final KafkaNotificationService kafkaNotificationService;

	@PostMapping
	public ResponseEntity<Void> send(
		@RequestBody KafkaNotificationSendRequest request
	) {
		kafkaNotificationService.send(request);
		return ResponseEntity.ok().build();
	}
}
