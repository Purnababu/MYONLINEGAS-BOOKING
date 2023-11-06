package com.example.Online_GasBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Online_GasBooking.entity.Cylinder;
import com.example.Online_GasBooking.service.ICylinderService;

@RestController
@RequestMapping("/api/cylinder-controller")
public class CylinderController {

	@Autowired
	private ICylinderService cylinderService;

	@PostMapping("/insert-cylinder")
	public ResponseEntity<Cylinder> insertCylinder(@RequestBody Cylinder cylinder) throws Exception {
		if (cylinder == null)
			throw new Exception("Object can not be empty");
		else
			return new ResponseEntity<Cylinder>(cylinderService.insertCylinder(cylinder), HttpStatus.CREATED);
	}

	@PutMapping("/update-cylinder")
	public ResponseEntity<Cylinder> updateCylinder(@RequestBody Cylinder cylinder) throws Exception {
		if (cylinder == null)
			throw new Exception("Object can not be empty");
		else
			return new ResponseEntity<Cylinder>(cylinderService.updateCylinder(cylinder), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete-cylinder/{cylinderId}")
	public ResponseEntity<Cylinder> deleteCylinder(@PathVariable long cylinderId) throws Exception {
		if (cylinderId == 0)
			throw new Exception("Id can not be empty");
		else
			return new ResponseEntity<Cylinder>(cylinderService.deleteCylinder(cylinderId), HttpStatus.CREATED);
	}

	@GetMapping("/view-cylinder/{type}")
	public ResponseEntity<List<Cylinder>> viewCylindersByType(@PathVariable String type) throws Exception {
		if (type == null)
			throw new Exception("Type can not be empty");
		else
			return new ResponseEntity<List<Cylinder>>(cylinderService.viewCylindersByType(type), HttpStatus.CREATED);
	}
}
