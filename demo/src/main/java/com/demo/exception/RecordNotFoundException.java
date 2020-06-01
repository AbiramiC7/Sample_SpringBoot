package com.demo.exception;

public class RecordNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException() {

        super("No Record found");
    }
}