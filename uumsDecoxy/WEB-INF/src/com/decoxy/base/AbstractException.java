/**
 * 
 */
package com.decoxy.base;

/**
 * @author Administrator
 * 
 */
public abstract class AbstractException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private String messageID;

	public AbstractException() {
		super();
	}

	public AbstractException(String message) {
		super(message);
	}

	public AbstractException(Throwable cause) {
		super(cause);
	}

	public AbstractException(String message, Throwable e) {
		super(message, e);
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

}
