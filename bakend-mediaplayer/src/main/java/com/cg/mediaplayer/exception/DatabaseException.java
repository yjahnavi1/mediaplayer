package com.cg.mediaplayer.exception;



public class DatabaseException  extends Exception{
	
   
	private static final long serialVersionUID = 1L;
	
	public DatabaseException() {
	       super();
	}

	public DatabaseException(String msg) {
       super(msg);
    }
}

