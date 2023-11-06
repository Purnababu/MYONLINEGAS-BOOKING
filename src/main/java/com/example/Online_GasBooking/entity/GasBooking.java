package com.example.Online_GasBooking.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GasBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long gasBookingId;
	
	@ManyToOne()                      //bi-directionalMapping
	private Customer customer;
	
	private LocalDate bookingDate;
	
	private boolean status;
	
	private float bill;

}