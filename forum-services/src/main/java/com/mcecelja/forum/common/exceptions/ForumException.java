package com.mcecelja.forum.common.exceptions;

import javax.servlet.ServletException;

public class ForumException extends ServletException {

	private final Object errorData;

	private final ForumError error;

	public ForumException(ForumError err) {
		this(err, null, null);
	}

	public ForumException(ForumError err, Throwable t) {
		this(err, null, t);
	}

	public ForumException(ForumError err, String errMsg, Throwable t) {
		super(t);
		this.error = err;
		this.errorData = errMsg;
	}

	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append(this.getClass().getName());
		sb.append(".superMsg[").append(super.getMessage());
		sb.append("].lderror[");
		if (this.error != null) {
			sb.append("<").append(this.error.name()).append(">");
		} else {
			sb.append("null");
		}
		sb.append("].errorData[");
		if (this.errorData != null) {
			sb.append(errorData);
		} else {
			sb.append("null");
		}
		sb.append("]}");
		return sb.toString();
	}

	public ForumError getError() {
		return error;
	}
}
