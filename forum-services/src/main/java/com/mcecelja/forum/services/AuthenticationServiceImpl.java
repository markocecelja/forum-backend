package com.mcecelja.forum.services;

import com.mcecelja.forum.common.dto.authentication.LoginResponseDTO;
import com.mcecelja.forum.common.dto.authentication.RegistrationRequestDTO;
import com.mcecelja.forum.common.exceptions.ForumError;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.domain.user.User;
import com.mcecelja.forum.domain.user.UserLogin;
import com.mcecelja.forum.repositories.user.UserLoginRepository;
import com.mcecelja.forum.repositories.user.UserRepository;
import com.mcecelja.forum.security.UserPrincipal;
import com.mcecelja.forum.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;

	private final UserLoginRepository userLoginRepository;

	private final UserService userService;

	private final JwtTokenProvider jwtTokenProvider;

	private final AuthenticationManager authenticationManager;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public LoginResponseDTO authenticateAndLogInUser(String username, String password) throws ForumException {
		try {
			User user = userService.findUserByUsername(username);

			if (user != null) {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								username,
								password
						)
				);

				UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
				String jwt = jwtTokenProvider.generateSessionToken(userPrincipal.getId());
				return new LoginResponseDTO(jwt);

			} else {
				log.warn("Bad credentials!");
				throw new ForumException(ForumError.BAD_CREDENTIALS);
			}
		} catch (Exception e) {
			log.warn("Bad credentials!");
			throw new ForumException(ForumError.BAD_CREDENTIALS);
		}
	}

	@Override
	public void registerUser(RegistrationRequestDTO registrationRequestDTO) throws ForumException {

		User user = new User();

		if(userLoginRepository.existsByUsernameIgnoreCase(registrationRequestDTO.getUsername())) {
			log.warn("User registration failed: username already in use!");
			throw new ForumException(ForumError.USERNAME_ALREADY_IN_USE);
		}

		if(!registrationRequestDTO.getPassword().equals(registrationRequestDTO.getConfirmationPassword())) {
			log.warn("User registration failed: password mismatch!");
			throw new ForumException(ForumError.PASSWORD_MISMATCH);
		}

		EmailValidator emailValidator = EmailValidator.getInstance();

		if(!emailValidator.isValid(registrationRequestDTO.getEmail())) {
			log.warn("User registration failed: invalid e-mail address!");
			throw new ForumException(ForumError.INVALID_EMAIL_ADDRESS);
		}

		user.setFirstName(registrationRequestDTO.getFirstName());
		user.setLastName(registrationRequestDTO.getLastName());

		user.setUserLogin(new UserLogin(null, registrationRequestDTO.getUsername(), bCryptPasswordEncoder.encode(registrationRequestDTO.getPassword()), user));

		userRepository.save(user);
	}
}
