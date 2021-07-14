package com.mcecelja.forum.utils;

import com.mcecelja.forum.common.exceptions.ForumError;
import com.mcecelja.forum.common.exceptions.ForumException;
import io.jsonwebtoken.JwtException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (ForumException e) {
			response.getWriter().write( ResponseMessage.packageAndJsoniseError(e.getError()));
		} catch (JwtException e) {
			response.getWriter().write( ResponseMessage.packageAndJsoniseError(ForumError.SESSION_EXPIRED));
		}
	}

}