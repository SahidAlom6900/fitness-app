package com.technoelevate.fitness.service;

import com.technoelevate.fitness.dto.User;
import com.technoelevate.fitness.pojo.UserPojo;

public interface UserService {
	User addUser(UserPojo userPojo);
	
	User deleteUser(String userName);

	User addCoachToUser(int coachId);

	User addOrderToUser(int productId,int productCount);

	User addFitnessPlanToUser(int planId);
	
	User addSteps(int steps);

	User getUser(String userName);
}
