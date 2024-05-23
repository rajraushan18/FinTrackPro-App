package com.expense.user.deserializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.expense.user.entities.UserInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserInfoDeserializer implements Deserializer<UserInfoDto>{

	
	@Override public void close() {
    }
    @Override public void configure(Map<String, ?> arg0, boolean arg1) {
    }

    @Override
    public UserInfoDto deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        UserInfoDto user = null;
        try {
            user = mapper.readValue(arg1, UserInfoDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
	
	
}
