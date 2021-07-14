package com.mcecelja.forum.rest;

import com.mcecelja.forum.common.dto.authentication.LoginRequestDTO;
import com.mcecelja.forum.common.dto.authentication.LoginResponseDTO;
import com.mcecelja.forum.common.dto.authentication.RegistrationRequestDTO;
import com.mcecelja.forum.common.dto.authentication.ResetPasswordRequestDTO;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.services.AuthenticationService;
import com.mcecelja.forum.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<ResponseMessage<LoginResponseDTO>> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws ForumException {

		LoginResponseDTO loginResponseDTO = authenticationService.authenticateAndLogInUser(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
		return ResponseEntity.ok(new ResponseMessage<>(loginResponseDTO));
	}

	@PostMapping("/registration")
	public ResponseEntity<ResponseMessage<String>> registerUser(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) throws ForumException {
		authenticationService.registerUser(registrationRequestDTO);
		return ResponseEntity.ok(new ResponseMessage<>(""));
	}

	@PutMapping("/resetPassword")
	public ResponseEntity<ResponseMessage<String>> resetPassword(@Valid @RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO) throws ForumException {
		authenticationService.resetPassword(resetPasswordRequestDTO);
		return ResponseEntity.ok(new ResponseMessage<>(""));
	}
}
