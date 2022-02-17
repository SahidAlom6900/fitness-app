package com.technoelevate.fitness.service;

import org.springframework.web.multipart.MultipartFile;

import com.technoelevate.fitness.dto.Admin;
import com.technoelevate.fitness.pojo.AdminPojo;

public interface AdminService {
	Admin addAdmin(AdminPojo adminPojo);

	Admin fetchAdminDetails(int adminId);

	Admin addCoach(MultipartFile multipartFile, String data);

	Admin addPlan(AdminPojo adminPojo);

	Admin addProduct(MultipartFile multipartFile, String data);

	Admin addTransformation(MultipartFile multipartFile, String data);
}
