package com.example.Online_GasBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Online_GasBooking.entity.Bank;
import com.example.Online_GasBooking.service.IBankService;
import com.onlinegasbooking.exceptions.BankAlreadyExistsException;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/api/bank")
public class BankController {

	@Autowired
	private IBankService bankService;

	@PostMapping("/add-bank")
	public ResponseEntity<?> insertBank(@RequestBody Bank bank) throws BankAlreadyExistsException {
		Bank b = bankService.insertBank(bank);

		return new ResponseEntity<>(b, HttpStatus.OK);
	}

	@PutMapping("/update-bank")
	public ResponseEntity<?> updateBank(@RequestBody Bank bank) throws ResourceNotFoundException {
		Bank update = bankService.updateBank(bank);

		return new ResponseEntity<>(update, HttpStatus.OK);
	}

	@PutMapping("/delete-bank")
	public ResponseEntity<?> deleteBank(@RequestBody Bank bank) throws ResourceNotFoundException {
		Bank delete = bankService.deleteBank(bank);

		return new ResponseEntity<>(delete, HttpStatus.OK);
	}

}
