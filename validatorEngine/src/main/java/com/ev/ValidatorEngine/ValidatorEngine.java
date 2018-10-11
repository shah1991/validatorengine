package com.ev.ValidatorEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.springframework.stereotype.Component;

import com.ev.constant.Constants;
import com.ev.model.BusinessException;
import com.ev.model.ValidationItem;
@Component
public class ValidatorEngine {

	private List manandatoryValidatorlist;
	private List dependentValidatorlist;
	private MultiMap exceptions;

	public List<ValidationItem> getManandatoryValidatorlist() {
		return manandatoryValidatorlist;
	}

	public void setManandatoryValidatorlist(ValidationItem item) {
		this.manandatoryValidatorlist.add(item);
	}

	public List<ValidationItem> getDependentValidatorlist() {
		return dependentValidatorlist;
	}

	public void setDependentValidatorlist(ValidationItem item) {
		this.dependentValidatorlist.add(item);
	}
	public MultiMap getExceptions() {
		return exceptions;
	}

	public void setExceptions(MultiMap exceptions) {
		this.exceptions = exceptions;
	}

	public ValidatorEngine() {
		manandatoryValidatorlist = new ArrayList<>();
		dependentValidatorlist = new ArrayList<>();
		exceptions=new MultiValueMap();
	}

	public <T> void addValidator(T t, Predicate p, String type) {
		ValidationItem<T> item = new ValidationItem<>();
		item.setPredicate(p);
		item.setValidationInput(t);
		item.setValidationType(type);
		
		if(type.equals(Constants.VALIDATION_DEPENDENT)) setDependentValidatorlist(item);
		else setManandatoryValidatorlist(item);
		
		System.out.println("Validators:  "+getDependentValidatorlist().size());

	}

	public MultiMap validate() {
		System.out.println("Validators:  "+getDependentValidatorlist().size());
		
		/*
		For Dependent & Independent Validators.
		*/
		
		getManandatoryValidatorlist().forEach(runValidator);
		if(getExceptions().size()!=0) return getExceptions();
		
		else getDependentValidatorlist().forEach(runValidator);
		return getExceptions();
	}

	private Consumer<ValidationItem> runValidator = item -> {
		
		try {
			System.out.print(item.getValidationInput());
			item.getPredicate().test(item.getValidationInput());
		} catch (RuntimeException e) {
			if(e instanceof BusinessException)
				getExceptions().put(item.getValidationType(), e.getMessage());
			else throw e;
		}
	};
}
