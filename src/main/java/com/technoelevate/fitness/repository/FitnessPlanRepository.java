package com.technoelevate.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.fitness.dto.FitnessPlan;

public interface FitnessPlanRepository extends JpaRepository<FitnessPlan, Integer> {
	FitnessPlan findByPlanId(int planId);
}
