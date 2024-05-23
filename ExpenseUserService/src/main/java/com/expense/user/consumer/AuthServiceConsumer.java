package com.expense.user.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.expense.user.entities.UserInfo;
import com.expense.user.entities.UserInfoDto;
import com.expense.user.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor; 

@Service
@RequiredArgsConstructor
public class AuthServiceConsumer {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@KafkaListener(topics="${spring.kafka.topic-json.name}", groupId="${spring.kafka.consumer.group-id}")
	public void listen(UserInfoDto eventData) {
		try {
			//make it transactional to handle idempotancy and validate email, phone etc -> can use redis distributed lock
			System.out.println(eventData.getUserId());
			userService.createOrUpdateUser(eventData);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("AuthServiceConsumer: Exception is thrown while consuming kafka event");
		}
	}
	
	
	
}
