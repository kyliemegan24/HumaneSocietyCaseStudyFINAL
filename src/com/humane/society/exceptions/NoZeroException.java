package com.humane.society.exceptions;


// custom exception use for ensuring that id isn't entered as "0"

public class NoZeroException extends Exception {
	public NoZeroException(String msg) {
		super(msg);
	}
}