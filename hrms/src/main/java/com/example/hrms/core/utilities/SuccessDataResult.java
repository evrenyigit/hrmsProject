package com.example.hrms.core.utilities;


public class SuccessDataResult<T> extends DataResult<T>{

	public SuccessDataResult(String message, T data) {
		super(true, message, data);
	}
	
	public SuccessDataResult(T data) {
		super(true,data);
	}


}
