package com.ev.model;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class ValidationItem<T> {

	private T validationInput;
	private Predicate predicate;
	private String validationType;

	public T getValidationInput() {
		return validationInput;
	}

	public void setValidationInput(T validationInput) {
		this.validationInput = validationInput;
	}

	public Predicate getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}

	public String getValidationType() {
		return validationType;
	}

	public void setValidationType(String validationType) {
		this.validationType = validationType;
	}

}
