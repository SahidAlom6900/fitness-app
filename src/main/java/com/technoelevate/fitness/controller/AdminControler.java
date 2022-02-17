package com.technoelevate.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.technoelevate.fitness.dto.Admin;
import com.technoelevate.fitness.pojo.AdminPojo;
import com.technoelevate.fitness.response.ResponseMessage;
import com.technoelevate.fitness.service.AdminService;

@RestController
@RequestMapping("api/v1")
public class AdminControler {
	@Autowired
	private AdminService adminService;

	@PostMapping("admin")
	public ResponseEntity<ResponseMessage> addAdmin(@RequestBody AdminPojo adminPojo) {
		Admin admin = adminService.addAdmin(adminPojo);
		if (admin != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", admin), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", admin), HttpStatus.BAD_REQUEST);
	}// end addAdmin method

	@GetMapping("admin/{adminId}")
	public ResponseEntity<ResponseMessage> fetchAdmin(@PathVariable("adminId") int adminId) {
		Admin admin = adminService.fetchAdminDetails(adminId);
		if (admin != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", admin), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", admin), HttpStatus.BAD_REQUEST);
	}// end fetchAdmin method

	@PostMapping("coach")
	public ResponseEntity<ResponseMessage> addCoach(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("data") String data) {
		Admin admin = adminService.addCoach(multipartFile, data);
		if (admin != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", admin), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", admin), HttpStatus.BAD_REQUEST);
	}// end addCoach method

	@PostMapping("plan")
	public ResponseEntity<ResponseMessage> addPlan(@RequestBody AdminPojo adminPojo) {
		Admin admin = adminService.addPlan(adminPojo);
		if (admin != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", admin), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", admin), HttpStatus.BAD_REQUEST);
	}// end addPlan method

	@PostMapping("product")
	public ResponseEntity<ResponseMessage> addProduct(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("data") String data) {
		Admin admin = adminService.addProduct(multipartFile, data);
		if (admin != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", admin), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", admin), HttpStatus.BAD_REQUEST);
	}// end addProduct method

	@PostMapping("transformtion")
	public ResponseEntity<ResponseMessage> addTransformation(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("data") String data) {
		Admin admin = adminService.addTransformation(multipartFile, data);
		if (admin != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", admin), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", admin), HttpStatus.BAD_REQUEST);
	}// end addTransformtion method
}
