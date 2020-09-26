package com.realpage.rpncalculator.userenter.factory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.realpage.rpncalculator.userenter.UserEnter;
import com.realpage.rpncalculator.userenter.UserEntry;
import com.realpage.rpncalculator.userenter.model.DigitalUserEntry;

public class DefaultUserEnterFactory implements UserEnter {

	private static final String CTRL_C = "\u0003";
	private static final String EXIT_VAL = "q";
	//private static final String REGEX_DIGIT_PATTERN = "^-*\\d+$";
	private static final String REGEX_DIGIT_PATTERN ="[+-]?([0-9]*[.])?[0-9]+";

	private Scanner scanner;

	public DefaultUserEnterFactory() {
		this(System.in);
	}

	public DefaultUserEnterFactory(InputStream in) {
		this.scanner = new Scanner(in);
	}

	@Override
	public List<UserEntry> getUserInput() {
		List<UserEntry> userEntries = new ArrayList<>();
		// User interactive mode
		String userEntered = scanner.nextLine();
		if(EXIT_VAL.equalsIgnoreCase(userEntered)){
System.out.println("Thanks for using this app");
			Runtime.getRuntime().exit(0);
		}
		/*if (CTRL_C.equalsIgnoreCase(userEntered))
			throw new NoSuchElementException("No line found");*/

		
		if (StringUtils.isNoneBlank(userEntered)) {
			String[] strings = userEntered.split(UserEnter.SPACE);
			for (String string : strings) {
				Optional<UserEntry> userEntry = this.constructUserEntry(string);
				if (userEntry.isPresent()) {
					userEntries.add(userEntry.get());
				}
			}
		}
		return userEntries;
	}

	public Optional<UserEntry> constructUserEntry(String userEntered) {
		Optional<UserEntry> userEntry = Optional.empty();
		
		if (StringUtils.isNotBlank(userEntered)) {
			//if (StringUtils.isNumeric(userEntered)) {
			if (userEntered.matches(REGEX_DIGIT_PATTERN)) {
				userEntry = getDigitalUserEntry(userEntered);
			}
			else {
				userEntry = getOperatorUserEntry(userEntered);
			}
		}
		return userEntry;
	}

	protected Optional<UserEntry> getOperatorUserEntry(String userEntered) {
		Optional<UserEntry> userEntry = Optional.empty();
		
		try {
			userEntry = OperatorFactory.getOperator(userEntered);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return userEntry;
	}

	protected Optional<UserEntry> getDigitalUserEntry(final String userEntered) {
		Optional<UserEntry> userEntry = Optional.empty();
		
		try {
			userEntry = Optional.of(new DigitalUserEntry(userEntered));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return userEntry;
	}



}
