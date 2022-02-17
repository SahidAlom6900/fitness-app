package com.technoelevate.fitness.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@JsonIgnoreProperties({ "password" })
public class Admin implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	
	private String adminName;
	
	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private List<Coach> coachs;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "adminId")
	private List<Product> products;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "adminId")
	private List<FitnessPlan> fitnessPlans;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "adminId")
	private List<Transformation> transformations;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ADMIN_ROLE", joinColumns = { @JoinColumn(name = "adminId") }, inverseJoinColumns = {
			@JoinColumn(name = "roleId") })
	private List<Role> roles;
}
