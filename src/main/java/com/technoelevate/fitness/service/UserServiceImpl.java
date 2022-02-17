package com.technoelevate.fitness.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.technoelevate.fitness.dto.Coach;
import com.technoelevate.fitness.dto.FitnessPlan;
import com.technoelevate.fitness.dto.Order;
import com.technoelevate.fitness.dto.Product;
import com.technoelevate.fitness.dto.User;
import com.technoelevate.fitness.pojo.UserPojo;
import com.technoelevate.fitness.repository.CoachRepository;
import com.technoelevate.fitness.repository.FitnessPlanRepository;
import com.technoelevate.fitness.repository.ProductRepository;
import com.technoelevate.fitness.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CoachRepository coachRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private FitnessPlanRepository planRepository;
//	@Autowired
//	private Principal principal;
	private String userName = "Sahid Alom";

	@Value("${steps.coin:1000}")
	private int stepsPerCoin;
	@Value("${rupees.coin:10}")
	private int rupeesPerCoin;

	@Override
	public User addUser(UserPojo userPojo) {
		User user = new User();
		BeanUtils.copyProperties(userPojo, user);
		return userRepository.save(user);
	}

	@Override
	public User addCoachToUser(int coachId) {
//		String userName = principal.getName();
		User user = userRepository.findByUserName(userName);
		Coach coach = coachRepository.findByCoachId(coachId);
		if (user == null || coach == null) {
			System.out.println(user);
			return null;
		}
		user.getCoachs().add(coach);
		return userRepository.save(user);
	}

	@Override
	public User addOrderToUser(int productId, int productCount) {
		User user = userRepository.findByUserName(userName);
		Product product = productRepository.findByProductId(productId);
		if (user == null || product == null)
			return null;
		double countedPrice = product.getPrice() * productCount;
		double grandTotal =(user.getCoins() * rupeesPerCoin);
		if ( grandTotal < countedPrice) 
			return null;
		user.setCoins((int) (grandTotal - countedPrice) / rupeesPerCoin);
		user.setSteps(
				(int) (((grandTotal - countedPrice) % rupeesPerCoin) / rupeesPerCoin * stepsPerCoin) + user.getSteps());
		Order order0 = new Order(LocalDate.now(), productCount, countedPrice, "Ordered");
		List<Product> products = order0.getProducts() == null ? new ArrayList<>() : order0.getProducts();
		products.add(product);
		order0.setProducts(products);
		user.getOrders().add(order0);
		return userRepository.save(user);
	}

	@Override
	public User addFitnessPlanToUser(int planId) {
		User user = userRepository.findByUserName(userName);
		FitnessPlan plan = planRepository.findByPlanId(planId);
		if (user == null || plan == null) {
			return null;}
		user.setFitnessPlan(plan);
		return userRepository.save(user);
	}

	@Override
	public User addSteps(int steps) {
		User user = userRepository.findByUserName(userName);
		int coins = (user.getSteps() + steps) / stepsPerCoin;
		steps = (user.getSteps() + steps) % stepsPerCoin;
		user.setCoins(user.getCoins() + coins);
		user.setSteps(steps);
		return userRepository.save(user);
	}

	@Override
	public User deleteUser(String userName) {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			return null;
		}
		userRepository.delete(user);
		return user;
	}

	@Override
	public User getUser(String userName) {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			return null;
		}
		return user;
	}

}
