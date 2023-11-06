package com.example.Online_GasBooking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_GasBooking.entity.Cylinder;
import com.example.Online_GasBooking.repo.ICylinderRepository;
import com.example.Online_GasBooking.service.ICylinderService;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

@Service
public class CylinderServiceImpl implements ICylinderService {
	@Autowired
	private ICylinderRepository icr;

	@Override
	public Cylinder insertCylinder(Cylinder cylinder) {

		Cylinder cylinder1 = icr.save(cylinder);
		return cylinder1;
	}

	@Override
	public Cylinder updateCylinder(Cylinder cylinder) throws ResourceNotFoundException {

		Cylinder cylinder2 = icr.findById(cylinder.getCylinderId()).orElseThrow(
				() -> new ResourceNotFoundException("Cylinder doent exist with id:" + cylinder.getCylinderId()));

		cylinder2.setCylinderId(cylinder.getCylinderId());
		cylinder2.setPrice(cylinder.getPrice());
		cylinder2.setStrapColor(cylinder.getStrapColor());
		cylinder2.setType(cylinder.getType());
		cylinder2.setWeight(cylinder.getWeight());

		icr.save(cylinder2);

		return cylinder2;
	}

	@Override
	public Cylinder deleteCylinder(long cylinderId) throws ResourceNotFoundException {
		Cylinder cylinder3 = icr.findById(cylinderId)
				.orElseThrow(() -> new ResourceNotFoundException("Cylinder doent exist with id:" + cylinderId));

		icr.delete(cylinder3);

		return cylinder3;
	}

	@Override
	public List<Cylinder> viewCylindersByType(String type) throws ResourceNotFoundException {

		List<Cylinder> emplist = icr.findAll().stream().filter(cy -> cy.getType().equalsIgnoreCase(type))
				.collect(Collectors.toList());
		if (emplist.isEmpty()) {

			throw new ResourceNotFoundException("No cylinders found for this type :" + type);
		} else
			return emplist;
	}

}
