package com.cg.mediaplyer.login.exception;


public class DuplicateRecordException  extends Exception{
	
	private static final long serialVersionUID = 1L;

	public DuplicateRecordException() {
		super();
	}
	public DuplicateRecordException(String msg) {
		super(msg);
	}
}
