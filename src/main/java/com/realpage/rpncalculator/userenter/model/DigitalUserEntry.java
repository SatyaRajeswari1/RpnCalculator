package com.realpage.rpncalculator.userenter.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.realpage.rpncalculator.history.record.OperationRecord;
import com.realpage.rpncalculator.storage.Storage;
import com.realpage.rpncalculator.userenter.UserEntry;

public class DigitalUserEntry implements UserEntry {

	private BigDecimal digits;
	
	public DigitalUserEntry(final String userEntered) {
		this.digits = new BigDecimal(userEntered);
	}
	
	@Override
	public void calculate(Storage storage) {
		storage.pushDigit(digits);
		OperationRecord record = toOperationRecord().apply(digits);
		storage.pushOperationRecord(record);
	}

	static Function<BigDecimal, OperationRecord> toOperationRecord() {
		return d -> {List<BigDecimal> params = Arrays.asList(d);
			return new OperationRecord(params, null);
		};
	}

	@Override
	public String getEmptyStackErrorMessage(int counter) {
		return "";
	}

}
