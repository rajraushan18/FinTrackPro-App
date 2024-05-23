package com.expense.auth.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.expense.auth.eventProducer.UserInfoEvent;
import com.expense.auth.model.UserInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserInfoSerializer implements Serializer<UserInfoEvent>{

	
	 @Override
	    public void configure(Map<String, ?> map, boolean b) {
	    }

	    @Override
	    public byte[] serialize(String arg0, UserInfoEvent arg1) {
	        byte[] retVal = null;
	        ObjectMapper objectMapper = new ObjectMapper();
	        try {
	            retVal = objectMapper.writeValueAsString(arg1).getBytes();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return retVal;
	    }
	    
	    
	    @Override public void close() {
	    }
	
	
}
