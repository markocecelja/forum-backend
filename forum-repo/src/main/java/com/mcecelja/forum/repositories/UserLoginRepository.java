package com.mcecelja.forum.repositories;

import com.mcecelja.forum.domain.user.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

	UserLogin findUserLoginByUserId(Long userId);

	UserLogin findUserLoginByUsernameIgnoreCase(String username);
}
