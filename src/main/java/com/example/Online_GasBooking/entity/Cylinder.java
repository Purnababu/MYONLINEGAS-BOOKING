package com.example.Online_GasBooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cylinder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cylinderId;

	private String type;

	private float weight;

	private String strapColor;

	private float price;
}