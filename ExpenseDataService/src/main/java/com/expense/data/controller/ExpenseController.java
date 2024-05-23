package com.expense.data.controller;

import jakarta.websocket.server.PathParam;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.data.dto.ExpenseDto;
import com.expense.data.service.ExpenseService;

import java.util.List;

@RestController
public class ExpenseController {

	
	private final ExpenseService expenseService;

    @Autowired
    ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @GetMapping("/expense/v1/")
    public ResponseEntity<List<ExpenseDto>> getExpense(@PathParam("user_id") @NonNull String userId){
         try{
            List<ExpenseDto> expenseDtoList = expenseService.getExpenses(userId);
            return new ResponseEntity<>(expenseDtoList, HttpStatus.OK);
         }catch(Exception ex){
             return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
         }
    }
	
	
	
}
