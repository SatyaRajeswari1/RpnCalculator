package com.realpage.rpncalculator.userenter.operator;

import java.math.BigDecimal;
import java.util.function.Predicate;

import com.realpage.rpncalculator.history.record.OperationRecord;
import com.realpage.rpncalculator.storage.Storage;
import com.realpage.rpncalculator.userenter.UserEntry;
import com.realpage.rpncalculator.userenter.enums.OperatorsEnum;

public class Undo implements UserEntry {

	@Override
	public void calculate(Storage storage) {
		OperationRecord record = storage.popOperationRecord();
		
		UserEntry operator = record.getOperator();
		if (isNeedClearUpResult().test(operator)) {
			//Pop out the last digit from stack
			//if the last operator is NOT Clear
			storage.popDigit();
		}
		if (null != record.getOperator()) {
			//Last operation has a operator,
			//Restore those parameters
			for(BigDecimal digit : record.getParameters()) {
				storage.pushDigit(digit);
			}
		}
	}

	/**
	 * This method return true unless the operator is a
	 * Clear class
	 * 
	 * @return true if the opeartor is NOT a Clear class.
	 *  For example:
	 * 			isNeedClearUpResult.test(null) => true
	 * 			isNeedClearUpResult.test(Addition) => true
	 * 			isNeedClearUpResult.test(Subtraction) => true
	 * 			isNeedClearUpResult.test(Multiplication) => true
	 * 			isNeedClearUpResult.test(Division) => true
	 * 			isNeedClearUpResult.test(SquareRoot) => true
	 * 			isNeedClearUpResult.test(Undo) => true
	 * 			isNeedClearUpResult.test(Clear) => false
	 */
	static Predicate<UserEntry> isNeedClearUpResult() {
		return e -> ((null == e) || (!(e instanceof Clear)));
	}

	@Override
	public String getEmptyStackErrorMessage(int counter) {
		StringBuilder buf = new StringBuilder("Operator: ");
		
		buf.append(OperatorsEnum.UNDO.getCode());
				
		buf.append(" (position: ");
		buf.append(counter);
		buf.append("): insufficient parameters");
		
		return buf.toString();
	}
}
