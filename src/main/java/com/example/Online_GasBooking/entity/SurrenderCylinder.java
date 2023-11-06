package com.example.Online_GasBooking.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurrenderCylinder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long surrenderId;

	private LocalDate surrenderDate;

	@OneToOne
	private Customer customer;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Cylinder cylinder;

}