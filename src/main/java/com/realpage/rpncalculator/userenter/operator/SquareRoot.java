package com.realpage.rpncalculator.userenter.operator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import com.realpage.rpncalculator.history.record.OperationRecord;
import com.realpage.rpncalculator.storage.Storage;
import com.realpage.rpncalculator.userenter.UserEntry;
import com.realpage.rpncalculator.userenter.enums.OperatorsEnum;

public class SquareRoot implements UserEntry, USER_EXCEPTION {

	@Override
	public void calculate(Storage storage) {
		BigDecimal first = storage.popDigit();
		if (first.equals(first.abs())) {
			//Square root only works on positive number
			BigDecimal result = new BigDecimal(Math.sqrt(first.doubleValue())).setScale(UserEntry.DECIMAL_PLACES, BigDecimal.ROUND_DOWN);
			storage.pushDigit(result);
			OperationRecord record = this.getOperationRecord(first);
			storage.pushOperationRecord(record);
		}
		else {
			storage.pushDigit(first);
			System.err.println("Square root cannot be applied to " + first.stripTrailingZeros());
		}
	}

	protected OperationRecord getOperationRecord(BigDecimal digit) {
		List<BigDecimal> params = Arrays.asList(digit);
		return new OperationRecord(params, this);
	}

	@Override
	public String getEmptyStackErrorMessage(int counter) {
		StringBuilder buf = new StringBuilder("Operator: ");
		
		buf.append(OperatorsEnum.SQUAREROOT.getCode());
				
		buf.append(" (position: ");
		buf.append(counter);
		buf.append("): insufficient parameters");
		
		return buf.toString();
	}

}
