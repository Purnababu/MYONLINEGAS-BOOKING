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

import com.example.Online_GasBooking.entity.Customer;
import com.example.Online_GasBooking.service.ICustomerService;
import com.onlinegasbooking.exceptions.InvalidCredentials;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/api/customer-controller")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@PostMapping("/insert-customer")
	public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer) throws Exception {
		if (customer == null)
			throw new Exception("Object can not be null");
		else
			return new ResponseEntity<Customer>(customerService.insertCustomer(customer), HttpStatus.CREATED);
	}

	@PutMapping("/update-customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws Exception {
		if (customer == null)
			throw new Exception("Object can not be null");
		else
			return new ResponseEntity<Customer>(customerService.updateCustomer(customer), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete-customer/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable long customerId) throws Exception {
		if (customerId == 0)
			throw new Exception("Object can not be null");
		else
			return new ResponseEntity<Customer>(customerService.deleteCustomer(customerId), HttpStatus.CREATED);
	}

	@GetMapping("/view-allCustomers")
	public ResponseEntity<List<Customer>> viewCustomers() throws ResourceNotFoundException {
		return new ResponseEntity<List<Customer>>(customerService.viewCustomers(), HttpStatus.CREATED);
	}

	@GetMapping("/view-customer/{customerId}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable long customerId) throws Exception {
		if (customerId == 0)
			throw new Exception("Id can not be null");
		else
			return new ResponseEntity<Customer>(customerService.deleteCustomer(customerId), HttpStatus.CREATED);
	}

	@GetMapping("/validate-customer/{username}/{password}")
	public ResponseEntity<Customer> validateCustomer(@PathVariable String username, @PathVariable String passsword)
			throws Exception {
		if (username == null || passsword == null || username == "" || passsword == "")
			throw new InvalidCredentials("Entered values are not allowed");
		else
			return new ResponseEntity<Customer>(customerService.validateCustomer(username, passsword),
					HttpStatus.CREATED);
	}

}
