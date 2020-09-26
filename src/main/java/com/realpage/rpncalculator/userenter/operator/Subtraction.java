package com.realpage.rpncalculator.userenter.operator;

import java.math.BigDecimal;

import com.realpage.rpncalculator.history.record.OperationRecord;
import com.realpage.rpncalculator.storage.Storage;
import com.realpage.rpncalculator.userenter.enums.OperatorsEnum;

public class Subtraction extends BiOperator {

	@Override
	protected void performDetailOperation(Storage storage) {
		BigDecimal first = storage.popDigit();
		BigDecimal second = storage.popDigit();
		BigDecimal result = second.subtract(first);
		storage.pushDigit(result);
		OperationRecord record = this.getOperationRecord(first, second);
		storage.pushOperationRecord(record);
	}

	@Override
	public String getEmptyStackErrorMessage(int counter) {
		StringBuilder buf = new StringBuilder("Operator: ");
		
		buf.append(OperatorsEnum.SUBTRACTION.getCode());
				
		buf.append(" (position: ");
		buf.append(counter);
		buf.append("): insufficient parameters");
		
		return buf.toString();
	}

}
