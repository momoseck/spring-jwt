package com.api.springsecurityjwt.repository;

import com.api.springsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
   User findUserByUsername(String username);
}
