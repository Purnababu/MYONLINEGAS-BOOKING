package com.example.Online_GasBooking.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "customer_id")
public class Customer extends AbstractUser {
	
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Cylinder cylinder;
	
	//private int bankID;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Bank bank;
	
	private long accountNumber;
	
	private String ifscNo;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "customer")
	private List<GasBooking> bookings;

}


