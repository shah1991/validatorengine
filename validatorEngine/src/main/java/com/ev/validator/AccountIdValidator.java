package com.ev.validator;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.ev.model.BusinessException;
@Component
public class AccountIdValidator {

	public Predicate<String> isValidAccountId(String id){
        return p -> {if(!p.equals(id)) throw new BusinessException("Invalid ID Entered");
			 return true;};
    }
	
}
