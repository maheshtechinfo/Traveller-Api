package com.nt.exception;

import java.util.function.Supplier;

import com.nt.vo.TravellerVO;

public class TravellerNotFoundException extends RuntimeException {

	public TravellerNotFoundException() {
		super();
	}
	
	public TravellerNotFoundException(String msg) {
		super(msg);
	}
		
	

}
