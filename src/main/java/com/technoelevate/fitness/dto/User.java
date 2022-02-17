package com.technoelevate.fitness.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "user_details")
@JsonIgnoreProperties({ "password" })
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_coach", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = {
			@JoinColumn(name = "coachId") })
	private List<Coach> coachs;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private List<Order> orders;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "planId")
	private FitnessPlan fitnessPlan;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = {
			@JoinColumn(name = "roleId") })
	private List<Role> roles;
}
