package com.humane.society.exceptions;

//custom exception used for ensuring that cats, dogs, employees, and locations can't have duplicate IDs entered in the db
// each dog, cat, location, or employee must have their own unique id

public class NoDuplicateException extends Exception {
	public NoDuplicateException(String msg) {
		super(msg);
	}
}
