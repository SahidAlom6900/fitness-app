package com.technoelevate.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.fitness.dto.Coach;

public interface CoachRepository extends JpaRepository<Coach, Integer> {
	Coach findByCoachId(int coachId);
}
