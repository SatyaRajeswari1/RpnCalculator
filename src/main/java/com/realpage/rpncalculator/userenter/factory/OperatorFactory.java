package com.realpage.rpncalculator.userenter.factory;

import java.util.Optional;

import com.realpage.rpncalculator.userenter.UserEntry;
import com.realpage.rpncalculator.userenter.enums.OperatorsEnum;
import com.realpage.rpncalculator.userenter.operator.Addition;
import com.realpage.rpncalculator.userenter.operator.Clear;
import com.realpage.rpncalculator.userenter.operator.Division;
import com.realpage.rpncalculator.userenter.operator.Multiplication;
import com.realpage.rpncalculator.userenter.operator.SquareRoot;
import com.realpage.rpncalculator.userenter.operator.Subtraction;
import com.realpage.rpncalculator.userenter.operator.Undo;

public class OperatorFactory {

	public static Optional<UserEntry> getOperator(final String userEntered) {
		Optional<UserEntry> userEntry = Optional.empty();
		
		try {
			OperatorsEnum operator = OperatorsEnum.fromString(userEntered);
			switch (operator) {
				case ADDITION:
					userEntry = Optional.of(new Addition());
					break;
				case SUBTRACTION:
					userEntry = Optional.of(new Subtraction());
					break;
				case MULTIPLICATION:
					userEntry = Optional.of(new Multiplication());
					break;
				case DIVISION:
					userEntry = Optional.of(new Division());
					break;
				case SQUAREROOT:
					userEntry = Optional.of(new SquareRoot());
					break;
				case CLEAR:
					userEntry = Optional.of(new Clear());
					break;
				case UNDO:
					userEntry = Optional.of(new Undo());
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return userEntry;
	}
}
