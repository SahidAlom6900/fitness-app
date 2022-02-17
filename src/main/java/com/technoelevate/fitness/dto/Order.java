package com.technoelevate.fitness.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@Table(name="order_details")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private LocalDate orderDate;
	
	private int productCount;
	
	private double totalPrice;
	
	private String status;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "order_product", joinColumns = { @JoinColumn(name = "orderId") }, inverseJoinColumns = {
			@JoinColumn(name = "productId") })
	private List<Product> products;

	public Order(LocalDate orderDate, int productCount, double totalPrice, String status) {
		this.orderDate = orderDate;
		this.productCount = productCount;
		this.totalPrice = totalPrice;
		this.status = status;
	}
	
	
}
