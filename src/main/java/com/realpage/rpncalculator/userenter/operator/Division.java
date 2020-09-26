package com.realpage.rpncalculator.userenter.operator;

import java.math.BigDecimal;

import com.realpage.rpncalculator.history.record.OperationRecord;
import com.realpage.rpncalculator.storage.Storage;
import com.realpage.rpncalculator.userenter.UserEntry;
import com.realpage.rpncalculator.userenter.enums.OperatorsEnum;

public class Division extends BiOperator {

	@Override
	protected void performDetailOperation(Storage storage) {
		BigDecimal first = storage.popDigit();
		//Handle Divisor is ZERO
		if (BigDecimal.ZERO.equals(first)) {
			storage.pushDigit(first);
			System.err.println("Divisor cannot be ZERO!");
			return;
		}
		BigDecimal second = storage.popDigit();
		BigDecimal total = second.divide(first, UserEntry.DECIMAL_PLACES, BigDecimal.ROUND_DOWN);
		storage.pushDigit(total);
		OperationRecord record = this.getOperationRecord(first, second);
		storage.pushOperationRecord(record);
	}

	@Override
	public String getEmptyStackErrorMessage(int counter) {
		StringBuilder buf = new StringBuilder("Operator: ");
		
		buf.append(OperatorsEnum.DIVISION.getCode());
				
		buf.append(" (position: ");
		buf.append(counter);
		buf.append("): insufficient parameters");
		
		return buf.toString();
	}

}
