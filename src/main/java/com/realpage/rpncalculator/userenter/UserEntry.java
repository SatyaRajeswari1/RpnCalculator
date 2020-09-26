package com.realpage.rpncalculator.userenter;

import com.realpage.rpncalculator.storage.Storage;

public interface UserEntry {

	public int DECIMAL_PLACES = 15;

	void calculate(Storage storage);
	
	String getEmptyStackErrorMessage(final int counter);
}
