package com.mcecelja.forum.context;

import com.mcecelja.forum.domain.user.User;

import java.time.ZonedDateTime;

public class AuthorizedRequestContext {

	private static ThreadLocal<User> currentUser = new ThreadLocal<>();

	private static ThreadLocal<ZonedDateTime> requestDateTime = new ThreadLocal<>();

	public static User getCurrentUser() {
		return currentUser.get();
	}

	public static void setCurrentUser(User user) {
		currentUser.set(user);
		requestDateTime.set(ZonedDateTime.now());
	}

	public static ZonedDateTime getRequestDateTime() {
		return requestDateTime.get();
	}

	public static void clear() {
		currentUser.set(null);
		requestDateTime.set(null);
	}
}