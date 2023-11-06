package com.example.Online_GasBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Online_GasBooking.entity.SurrenderCylinder;
import com.example.Online_GasBooking.service.ISurrenderCylinderService;

@RestController
@RequestMapping("/api/surrender-cylinder")
public class SurrenderCylinderController {

	@Autowired
	private ISurrenderCylinderService cylinderService;

	@PostMapping("/insert-surrendercylinder")
	public ResponseEntity<SurrenderCylinder> insertSurrenderCylinder(@RequestBody SurrenderCylinder sc,
			@PathVariable long customerId, @PathVariable long cylinderId) throws Exception {
		if (sc == null || customerId == 0 || cylinderId == 0)
			throw new Exception("One of the value is not valid");
		else
			return new ResponseEntity<SurrenderCylinder>(
					cylinderService.insertSurrenderCylinder(sc, customerId, cylinderId), HttpStatus.CREATED);
	}

	@PutMapping("/upadte-surrenderCylinder")
	public void updateSurrenderCylinder(@RequestBody SurrenderCylinder sc) throws Exception {
		if (sc == null)
			throw new Exception("One of the value is not valid");
		else {
			cylinderService.updateSurrenderCylinder(sc);
			System.out.println(new ResponseEntity<>(HttpStatus.CREATED));
		}
	}

	public void deleteSurrenderCylinder(SurrenderCylinder sc) throws Exception {
		if (sc == null)
			throw new Exception("One of the value is not valid");
		else {
			cylinderService.deleteSurrenderCylinder(sc);
			System.out.println(new ResponseEntity<>(HttpStatus.CREATED));
		}
	}

	public void countSurrendredCylinders() {

		cylinderService.countSurrendredCylinders();
	}
}
