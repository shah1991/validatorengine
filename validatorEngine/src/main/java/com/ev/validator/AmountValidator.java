package com.ev.validator;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.ev.model.BusinessException;
@Component
public class AmountValidator {

	public Predicate<Double> isAmountLimitValid(Double amt) {
        return p -> {if(p > amt) throw new BusinessException("Amount Exceeded"); return true;};
    }
}
