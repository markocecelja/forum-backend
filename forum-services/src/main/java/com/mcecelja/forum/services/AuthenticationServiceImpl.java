package com.mcecelja.forum.services;

import com.mcecelja.forum.common.dto.authentication.LoginResponseDTO;
import com.mcecelja.forum.common.exceptions.ForumError;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.domain.user.User;
import com.mcecelja.forum.security.UserPrincipal;
import com.mcecelja.forum.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserService userService;

	private final JwtTokenProvider jwtTokenProvider;

	private final AuthenticationManager authenticationManager;

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
}
