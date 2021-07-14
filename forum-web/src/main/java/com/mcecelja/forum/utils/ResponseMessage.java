package com.mcecelja.forum.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mcecelja.forum.common.exceptions.ForumError;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.common.utils.JsonSimpleHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class ResponseMessage<T> {
	private String status;

	private String errorCode;

	private String errorMessage;

	private T payload;

	public ResponseMessage(String status, ForumError error) {
		this(status, (error != null ? error.name() : null), (error != null ? error.desc() : null));
	}

	public ResponseMessage(T payload) {
		this("OK", null, null, payload);
	}

	public ResponseMessage(String status, String errorCode, String message) {
		this(status, errorCode, message, null);
	}

	public ResponseMessage(String status, String errorCode, String message, T payload) {
		this.status = status;
		this.errorCode = errorCode;
		this.errorMessage = message;
		this.payload = payload;
	}


	public static String getMsgAsJson(ResponseMessage msg) throws ForumException {
		if (msg.payload != null) {
			Object branch = msg.payload;
			msg.payload = null;
			return JsonSimpleHelper.combineAndSerialise(msg, "payload", branch);
		} else
			return JsonSimpleHelper.serialise(msg);
	}

	public static String packageAndJsoniseError(ForumError error) throws ForumException {
		ResponseMessage replyObject = new ResponseMessage("ERROR", error);
		return ResponseMessage.getMsgAsJson(replyObject);
	}
}
