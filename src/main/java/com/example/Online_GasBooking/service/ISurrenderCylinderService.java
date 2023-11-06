package com.example.Online_GasBooking.service;


import com.example.Online_GasBooking.entity.SurrenderCylinder;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

public interface ISurrenderCylinderService {

	public SurrenderCylinder insertSurrenderCylinder(SurrenderCylinder sc,long customerId,long cylinderId) throws ResourceNotFoundException;
	
	public void updateSurrenderCylinder(SurrenderCylinder sc) throws ResourceNotFoundException;
	
	public void deleteSurrenderCylinder(SurrenderCylinder sc) throws ResourceNotFoundException;
	
	public long countSurrendredCylinders();
	
}