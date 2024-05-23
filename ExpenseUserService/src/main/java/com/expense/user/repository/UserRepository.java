package com.expense.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.expense.user.entities.UserInfo;
import com.expense.user.entities.UserInfoDto;

@Repository
@EnableJpaRepositories
public interface UserRepository extends CrudRepository<UserInfo, String>{

	
	Optional<UserInfo> findByUserId(String userId); 
	
	
}
