package com.mcecelja.forum.repositories.user;

import com.mcecelja.forum.domain.user.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

	UserLogin findUserLoginByUserId(Long userId);

	UserLogin findUserLoginByUsernameIgnoreCase(String username);

	boolean existsByUsernameIgnoreCase(String username);
}
