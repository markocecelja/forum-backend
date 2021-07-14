package com.mcecelja.forum.services;

import com.mcecelja.forum.common.dto.user.UserDTO;
import com.mcecelja.forum.common.exceptions.ForumException;
import com.mcecelja.forum.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

	UserDetails findUserDetailsByUserId(Long userId);

	User findUserByUsername(String username);

	UserDTO getCurrentUserInfo();

	UserDTO getUser(Long userId) throws ForumException;

	UserDTO updateUser(Long userId, UserDTO userDTO) throws ForumException;

	void deleteUser(Long userId) throws ForumException;
}
