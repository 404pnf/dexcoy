package com.decoxy.common.exception;

import com.decoxy.base.AbstractException;

public class BusinessException extends AbstractException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(Throwable e) {
		super(e);
	}

	public BusinessException(String message, Throwable e) {
		super(message, e);
	}

}