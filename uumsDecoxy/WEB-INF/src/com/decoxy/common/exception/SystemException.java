package com.decoxy.common.exception;

import com.decoxy.base.AbstractException;

public class SystemException extends AbstractException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public SystemException() {
		super();
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Throwable e) {
		super(message, e);
		setMessageID(message);
	}

}
