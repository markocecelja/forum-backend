package com.mcecelja.forum.utils;

import com.mcecelja.forum.common.exceptions.ForumError;
import com.mcecelja.forum.common.exceptions.ForumException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler({ForumException.class})
	public ResponseEntity<Object> handleForumException(
			ForumException ex) {

		String response = null;
		try {
			response = ResponseMessage.packageAndJsoniseError(ex.getError());
		} catch (ForumException e) {

			e.printStackTrace();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
		String response = null;
		ex.printStackTrace();
		try {
			response = ResponseMessage.packageAndJsoniseError(ForumError.BAD_REQUEST);
		} catch (ForumException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleException(
			Exception ex) {

		String response = null;
		ex.printStackTrace();
		try {
			response = ResponseMessage.packageAndJsoniseError(ForumError.UNRECOGNIZED_EXCEPTION);
		} catch (ForumException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
