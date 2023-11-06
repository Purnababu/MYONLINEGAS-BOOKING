package com.example.Online_GasBooking.service;


import java.util.List;

import com.example.Online_GasBooking.entity.Cylinder;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

public interface ICylinderService {
	
	public Cylinder insertCylinder(Cylinder cylinder);
	
	public Cylinder updateCylinder(Cylinder cylinder) throws ResourceNotFoundException;
	
	public Cylinder deleteCylinder(long cylinderId) throws ResourceNotFoundException;
	
	public List<Cylinder> viewCylindersByType(String type) throws ResourceNotFoundException;
}
