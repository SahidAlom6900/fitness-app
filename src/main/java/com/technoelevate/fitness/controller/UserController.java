package com.technoelevate.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technoelevate.fitness.dto.User;
import com.technoelevate.fitness.pojo.UserPojo;
import com.technoelevate.fitness.response.ResponseMessage;
import com.technoelevate.fitness.service.UserService;

@RestController
@RequestMapping("api/v1")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("user")
	public ResponseEntity<ResponseMessage> addUser(@RequestBody UserPojo userPojo) {
		User user = userService.addUser(userPojo);
		if (user != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", user), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", user), HttpStatus.BAD_REQUEST);
	}// end addUser method
	@GetMapping("user/{userName}")
	public ResponseEntity<ResponseMessage> getUser(@PathVariable("userName") String userName) {
		User user = userService.getUser(userName);
		if (user != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", user), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", user), HttpStatus.BAD_REQUEST);
	}// end addUser method

	@PostMapping("user-plan/{planId}")
	public ResponseEntity<ResponseMessage> addFitnessPlanToUser(@PathVariable("planId") int planId) {
		User user = userService.addFitnessPlanToUser(planId);
		if (user != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", user), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", user), HttpStatus.BAD_REQUEST);
	}// end addFitnessPlanToUser method

	@PostMapping("user-order/{productId}/{productCount}")
	public ResponseEntity<ResponseMessage> addOrderToUser(@PathVariable("productId") int productId,
			@PathVariable("productCount") int productCount) {
		User user = userService.addOrderToUser(productId, productCount);
		if (user != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", user), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", user), HttpStatus.BAD_REQUEST);
	}// end addOrderToUser method

	@PostMapping("user-coach/{coachId}")
	public ResponseEntity<ResponseMessage> addCoachToUser(@PathVariable("coachId") int coachId) {
		User user = userService.addCoachToUser(coachId);
		if (user != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", user), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", user), HttpStatus.BAD_REQUEST);
	}// end addCoachToUser method

	@PostMapping("user-step/{steps}")
	public ResponseEntity<ResponseMessage> addStepsToUser(@PathVariable("steps") int steps) {
		User user = userService.addSteps(steps);
		if (user != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", user), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", user), HttpStatus.BAD_REQUEST);
	}// end addCoachToUser method
	@DeleteMapping("user/{userName}")
	public ResponseEntity<ResponseMessage> deleteUser(@PathVariable("userName") String userName) {
		User user = userService.deleteUser(userName);
		if (user != null)
			return new ResponseEntity<>(new ResponseMessage(false, "", user), HttpStatus.OK);
		return new ResponseEntity<>(new ResponseMessage(true, "", user), HttpStatus.BAD_REQUEST);
	}// end addCoachToUser method

}
