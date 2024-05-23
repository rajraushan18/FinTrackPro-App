package com.expense.data.consumer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.expense.data.dto.ExpenseDto;
import com.expense.data.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseConsumer{

	
	 private ExpenseService expenseService;

	    @Autowired
	    ExpenseConsumer(ExpenseService expenseService){
	        this.expenseService = expenseService;
	    }

	    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
	    public void listen(ExpenseDto eventData) {
	        try{
	            // Todo: Make it transactional, and check if duplicate event (Handle idempotency)
	            expenseService.createExpense(eventData);
	        }catch(Exception ex){
	            ex.printStackTrace();
	            System.out.println("AuthServiceConsumer: Exception is thrown while consuming kafka event");
	        }
	    }

	
	
	
}
