package com.mcecelja.forum.rest;

import com.mcecelja.forum.common.dto.user.UserDTO;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.services.UserService;
import com.mcecelja.forum.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

	private final UserService userService;

	@GetMapping("/{userId}")
	public ResponseEntity<ResponseMessage<UserDTO>> getUser(@PathVariable("userId") Long userId) throws ForumException {
		return ResponseEntity.ok(new ResponseMessage<>(userService.getUser(userId)));
	}

	@GetMapping("/currentUser")
	public ResponseEntity<ResponseMessage<UserDTO>> getCurrentUserInfo() {
		return ResponseEntity.ok(new ResponseMessage<>(userService.getCurrentUserInfo()));
	}

	@PutMapping("/{userId}")
	public ResponseEntity<ResponseMessage<UserDTO>> updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody UserDTO userDTO) throws ForumException {
		return ResponseEntity.ok(new ResponseMessage<>(userService.updateUser(userId, userDTO)));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ResponseMessage<String>> deleteUser(@PathVariable("userId") Long userId) throws ForumException {
		userService.deleteUser(userId);
		return ResponseEntity.ok(new ResponseMessage<>(""));
	}
}
