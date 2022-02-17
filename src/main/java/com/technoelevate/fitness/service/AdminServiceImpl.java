package com.technoelevate.fitness.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technoelevate.fitness.dto.Admin;
import com.technoelevate.fitness.dto.Coach;
import com.technoelevate.fitness.dto.Product;
import com.technoelevate.fitness.dto.Transformation;
import com.technoelevate.fitness.exception.AdminException;
import com.technoelevate.fitness.exception.FileNotFoundException;
import com.technoelevate.fitness.pojo.AdminPojo;
import com.technoelevate.fitness.repository.AdminRepository;
import com.technoelevate.fitness.repository.CoachRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private CoachRepository coachRepository;
	@Autowired
	private ObjectMapper mappper;

	@Value("${file.upload.location:E:\\fitness}")
	private String dir;

	private Path getPath(String fileName) {
		return Paths.get(this.dir + "\\" + fileName).toAbsolutePath().normalize();
	}

	@Override
	public Admin addAdmin(AdminPojo adminPojo) {
		List<Admin> admins = adminRepository.findAll();
		if (admins.isEmpty()) {
			Admin admin = new Admin();
			BeanUtils.copyProperties(adminPojo, admin);
			return adminRepository.save(admin);
		}
		return null;
	}

	@Override
	public Admin addCoach(MultipartFile multipartFile, String data) {
		AdminPojo adminPojo = getAdminPojo(data);
		Admin admin = adminRepository.findByAdminId(adminPojo.getAdminId());
		if (admin == null)
			return null;
		List<Coach> coachs = adminPojo.getCoachs();
		for (Coach coach : coachs) {
			String filename = addFile("Coach", multipartFile);
			coach.setImagePath(filename);
		}
		admin.getCoachs().addAll(coachs);
		return adminRepository.save(admin);
	}

	@Override
	public Admin addPlan(AdminPojo adminPojo) {
		Admin admin = adminRepository.findByAdminId(adminPojo.getAdminId());
		if (admin == null)
			return null;
		admin.getFitnessPlans().addAll(adminPojo.getFitnessPlans());
		return adminRepository.save(admin);
	}

	@Override
	public Admin addProduct(MultipartFile multipartFile, String data) {
		AdminPojo adminPojo = getAdminPojo(data);
		Admin admin = adminRepository.findByAdminId(adminPojo.getAdminId());
		if (admin == null)
			return null;
		List<Product> products = adminPojo.getProducts().stream().map(product -> {
			String filePath = addFile("Product", multipartFile);
			product.setProductImage(filePath);
			return product;
		}).collect(Collectors.toList());
		admin.getProducts().addAll(products);
		return adminRepository.save(admin);
	}

	@Override
	public Admin addTransformation(MultipartFile multipartFile, String data) {
		AdminPojo adminPojo = getAdminPojo(data);
		Admin admin = adminRepository.findByAdminId(adminPojo.getAdminId());
		if (admin == null)
			return null;
		List<Transformation> transformations = adminPojo.getTransformations().stream().map(transformation -> {
			String videoPath = addFile("Transformation", multipartFile);
			List<Coach> coachs = transformation.getCoachs().stream()
					.map(coach -> coachRepository.findByCoachId(coach.getCoachId())).collect(Collectors.toList());
			transformation.setVideoPath(videoPath);
			transformation.setCoachs(coachs);
			return transformation;
		}).collect(Collectors.toList());
		admin.getTransformations().addAll(transformations);
		return adminRepository.save(admin);
	}

	private AdminPojo getAdminPojo(String data) {
		try {
			return mappper.readValue(data, AdminPojo.class);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new AdminException("");
		}
	}

	private String addFile(String folderName, MultipartFile multipartFile) {
		try {
			Path dirLocation = getPath(folderName);
			if (multipartFile != null) {
				Files.createDirectories(dirLocation);
				String filename = multipartFile.getOriginalFilename();
				Path filePath = dirLocation.resolve(filename);
				Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
				return filePath.toString();
			} else {
				throw new FileNotFoundException("");
			}
		} catch (IOException | FileNotFoundException exception) {
			throw new FileNotFoundException(exception.getMessage());
		}
	}

	@Override
	public Admin fetchAdminDetails(int adminId) {
		Admin admin = adminRepository.findByAdminId(adminId);
		if (admin != null)
			return admin;
		return null;
	}

}
