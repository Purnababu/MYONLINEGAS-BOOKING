package com.example.Online_GasBooking.service;

import java.util.List;

import com.example.Online_GasBooking.entity.Customer;
import com.onlinegasbooking.exceptions.CustomerAlreadyExistsException;
import com.onlinegasbooking.exceptions.CustomerDoesntExistsException;
import com.onlinegasbooking.exceptions.InvalidCredentials;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

public interface ICustomerService {

	public Customer insertCustomer(Customer customer) throws CustomerAlreadyExistsException;
	
	public Customer updateCustomer(Customer customer) throws CustomerDoesntExistsException;
	
	public Customer deleteCustomer(long	 customerId) throws CustomerDoesntExistsException;
	
	public List<Customer> viewCustomers() throws ResourceNotFoundException;
	
	public Customer viewCustomer(long customerId) throws CustomerDoesntExistsException;
	
	public Customer validateCustomer(String username,String password) throws CustomerDoesntExistsException, InvalidCredentials;
	
	
}