package com.technoelevate.fitness.pojo;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.technoelevate.fitness.dto.Coach;
import com.technoelevate.fitness.dto.FitnessPlan;
import com.technoelevate.fitness.dto.Order;
import com.technoelevate.fitness.dto.Role;

import lombok.Data;

@Data
public class UserPojo {
	
	private int userId;
	
	private String userName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private LocalDate dob;
	
	private String email;
	
	private long phoneNumber;
	
	private String address;
	
	private String password;
	
	private String gender;
	
	private int steps;
	
	private int coins;

	private List<Coach> coachs;

	private List<Order> orders;

	private FitnessPlan fitnessPlan;
	
	private List<Role> roles;
}
