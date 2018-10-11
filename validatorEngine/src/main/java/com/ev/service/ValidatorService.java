package com.ev.service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.collections.MultiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.ev.ValidatorEngine.ValidatorEngine;
import com.ev.config.ResourceBundleSource;
import com.ev.constant.Constants;
import com.ev.validator.AccountIdValidator;
import com.ev.validator.AmountValidator;


@Service
public class ValidatorService {

	@Autowired 
	private ValidatorEngine engine;
	
	@Autowired
	private AccountIdValidator idValidator;
	
	@Autowired
	private AmountValidator amtValidator;
	
	@Autowired
	private ResourceBundleSource messageSource;
	
	
	public void serviceCall() {
		
		Locale locale=new Locale("en");
		System.out.println("Enter AccountId");
		Scanner s= new Scanner(System.in);
		
		engine.addValidator(s.next(),idValidator.isValidAccountId(Constants.ACCOUNT_ID),Constants.VALIDATION_MANDATORY);
		
		System.out.println("Enter the amount");
		s= new Scanner(System.in);
		
		engine.addValidator(s.nextDouble(),amtValidator.isAmountLimitValid(Constants.AMOUNT_LIMIT),Constants.VALIDATION_DEPENDENT);
		MultiMap errors=engine.validate();
		
		/*errors.forEach((k,v)->v=messageSource.getSource().getMessage(v, locale));*/
                
		
		System.out.println("");
		errors.forEach((k,v)->System.out.println("ValidationType : " + k + " Error Message : " + v));
	}
}
