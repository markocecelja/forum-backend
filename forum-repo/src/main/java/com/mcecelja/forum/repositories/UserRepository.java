package com.mcecelja.forum.repositories;

import com.mcecelja.forum.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
