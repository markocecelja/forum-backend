package com.mcecelja.forum.services;

import com.mcecelja.forum.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

	UserDetails findUserDetailsByUserId(Long userId);

	User findUserByUsername(String username);
}
