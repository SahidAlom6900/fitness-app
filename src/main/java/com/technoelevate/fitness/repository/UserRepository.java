package com.technoelevate.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.fitness.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUserId(int userId);

	User findByUserName(String userName);
}
