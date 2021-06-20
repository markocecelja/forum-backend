package com.mcecelja.forum.services;

import com.mcecelja.forum.common.dto.authentication.LoginResponseDTO;
import com.mcecelja.forum.common.dto.authentication.RegistrationRequestDTO;
import com.mcecelja.forum.common.exceptions.ForumException;

public interface AuthenticationService {

	LoginResponseDTO authenticateAndLogInUser(String username, String password) throws ForumException;

	void registerUser(RegistrationRequestDTO registrationRequestDTO) throws ForumException;
}
