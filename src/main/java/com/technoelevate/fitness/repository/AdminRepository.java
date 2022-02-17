package com.technoelevate.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.fitness.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Admin findByAdminId(int adminId);
}
