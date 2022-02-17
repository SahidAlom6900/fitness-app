package com.technoelevate.fitness.pojo;

import java.util.List;

import com.technoelevate.fitness.dto.Coach;
import com.technoelevate.fitness.dto.FitnessPlan;
import com.technoelevate.fitness.dto.Product;
import com.technoelevate.fitness.dto.Role;
import com.technoelevate.fitness.dto.Transformation;

import lombok.Data;

@Data
public class AdminPojo {
	private int adminId;

	private String adminName;

	private String password;

	private List<Coach> coachs;

	private List<Product> products;

	private List<FitnessPlan> fitnessPlans;

	private List<Transformation> transformations;

	private List<Role> roles;
}
