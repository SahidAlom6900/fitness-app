package com.technoelevate.fitness.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
public class Permission implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int permissionId;
	private String permissionName;
}
