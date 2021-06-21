package com.mcecelja.forum.repositories.user;

import com.mcecelja.forum.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
