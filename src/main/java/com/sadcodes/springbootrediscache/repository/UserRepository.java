package com.sadcodes.springbootrediscache.repository;

import com.sadcodes.springbootrediscache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
