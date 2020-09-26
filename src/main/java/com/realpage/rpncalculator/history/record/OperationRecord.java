package com.realpage.rpncalculator.history.record;

import java.math.BigDecimal;
import java.util.List;

import com.realpage.rpncalculator.userenter.UserEntry;
import org.apache.commons.collections4.CollectionUtils;

public class OperationRecord {

	private List<BigDecimal> parameters;
	private UserEntry operator;
	
	public List<BigDecimal> getParameters() {
		return parameters;
	}

	public UserEntry getOperator() {
		return operator;
	}

	public OperationRecord(List<BigDecimal> parameters, UserEntry operator) {
		if (CollectionUtils.isEmpty(parameters)) {
			throw new IllegalArgumentException("parameters cannot be null");
		}
		this.parameters = parameters;
		this.operator = operator;
	}
}
