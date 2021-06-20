package com.mcecelja.forum.context;

import com.mcecelja.forum.common.exceptions.ForumError;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.domain.user.User;
import com.mcecelja.forum.repositories.UserRepository;
import com.mcecelja.forum.security.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class ContextCreationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		String authToken = jwtUtil.resolveToken(httpServletRequest);
		if (authToken != null && !authToken.isEmpty()) {
			Long userId = jwtUtil.getUserIdFromSessionToken(authToken);
			Optional<User> userOptional = userRepository.findById(userId);

			if (!userOptional.isPresent()) {
				log.warn("Failed creating context: no active user for id {}!", userId);
				throw new ForumException(ForumError.SESSION_EXPIRED);
			}

			User user = userOptional.get();

			AuthorizedRequestContext.setCurrentUser(user);
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}