package com.mcecelja.forum.common.exceptions;

public enum ForumError {
	UNRECOGNIZED_EXCEPTION("Unrecognized exception!"),
	SESSION_EXPIRED("Session expired!"),
	JSON_PARSE_ERROR("Error while parsing JSON!"),
	BAD_REQUEST("Bad request!"),;

	private final String desc;


	ForumError(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return this.desc;
	}
}