package com.mcecelja.forum.common.exceptions;

public enum ForumError {
	UNRECOGNIZED_EXCEPTION("Unrecognized exception!"),
	SESSION_EXPIRED("Session expired!"),
	JSON_PARSE_ERROR("Error while parsing JSON!"),
	BAD_REQUEST("Bad request!"),
	BAD_CREDENTIALS("Bad credentials"),
	PASSWORD_MISMATCH("Password mismatch!"),
	INVALID_EMAIL_ADDRESS("Email is not in a valid format!"),
	USERNAME_ALREADY_IN_USE("Provided username is already in use!"),
	UNAUTHORIZED("Unauthorized request!"),
	USER_NOT_FOUND("User with provided id doesn't exist!"),
	NON_EXISTING_TOPIC("Topic with provided id doesn't exist!"),
	NON_EXISTING_COMMENT("Comment with provided id doesn't exist!");

	private final String desc;


	ForumError(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return this.desc;
	}
}
