package com.realpage.rpncalculator;

import java.io.InputStream;
import java.util.EmptyStackException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.realpage.rpncalculator.storage.DefaultStorage;
import com.realpage.rpncalculator.storage.Storage;
import com.realpage.rpncalculator.userenter.UserEnter;
import com.realpage.rpncalculator.userenter.UserEntry;
import com.realpage.rpncalculator.userenter.factory.DefaultUserEnterFactory;

public class RpnCalculator {

	private static final int ONE = 1;
	private UserEnter userEnter;
	private Storage storage;
	
	public RpnCalculator() {
		this(System.in);
	}
	
	public RpnCalculator(InputStream in) {
		if (null == in) {
			throw new IllegalArgumentException("InputStream cannot be null!");
		}
		
		this.userEnter = new DefaultUserEnterFactory(in);
		this.storage = new DefaultStorage();
	}
	
	public void start() {
		List<UserEntry> userEntries = null;
		AtomicInteger counter = new AtomicInteger(ONE);
		while( null != (userEntries = this.userEnter.getUserInput())) {
			for(UserEntry e : userEntries) {
				try {
					e.calculate(this.storage);
					counter.incrementAndGet();
				}
				catch (EmptyStackException ese) {
					System.err.println(formatErrorMessage(e, counter.get()));
					break;
				}
			}
			storage.printStack();
		}
	}

	protected String formatErrorMessage(UserEntry e, int counter) {
		return e.getEmptyStackErrorMessage(counter);
	}

	public Storage getStorage() {
		return storage;
	}

	public static void main(String[] argv) {
		System.out.print("> ");
		new RpnCalculator().start();
	}
}
