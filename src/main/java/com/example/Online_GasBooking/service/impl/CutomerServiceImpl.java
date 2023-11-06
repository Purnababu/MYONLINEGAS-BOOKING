package com.example.Online_GasBooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_GasBooking.entity.Customer;
import com.example.Online_GasBooking.repo.ICustomerRepository;
import com.example.Online_GasBooking.service.ICustomerService;
import com.onlinegasbooking.exceptions.CustomerAlreadyExistsException;
import com.onlinegasbooking.exceptions.CustomerDoesntExistsException;
import com.onlinegasbooking.exceptions.InvalidCredentials;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

@Service
public class CutomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository icr;

	final static Logger logger = org.slf4j.LoggerFactory.getLogger("GasBookingApplication.class");

	@Override
	public Customer insertCustomer(Customer customer) throws CustomerAlreadyExistsException {

		Optional<Customer> un = icr.getCustomerByUsername(customer.getUserName());

		if (un.isPresent())
			throw new CustomerAlreadyExistsException(
					"Customer already exists with username: " + customer.getUserName());
		else {
			customer.setAccountNumber((long) Math.random());
			logger.info("Customer inserted");
			return icr.save(customer);
		}

	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerDoesntExistsException {

		Customer u = icr.getCustomerByUsername(customer.getUserName())
				.orElseThrow(() -> new CustomerDoesntExistsException(
						"Customer doesnt exists with username: " + customer.getUserName()));
		// u.setAccountNumber(customer.getAccountNumber());
		u.setAddress(customer.getAddress());
		// u.setBank(u.getBank());
		u.setIfscNo(u.getIfscNo());
		u.setEmail(u.getEmail());
		u.setMobileNumber(u.getMobileNumber());
		u.setUserName(u.getUserName());
		u.setPassword(u.getPassword());
		logger.info("Customer details updated");
		icr.save(u);
		return u;
	}

	@Override
	public Customer deleteCustomer(long customerId) throws CustomerDoesntExistsException {
		Customer d = icr.findById(customerId)
				.orElseThrow(() -> new CustomerDoesntExistsException("Customer doent exists with id: " + customerId));

		icr.delete(d);
		logger.warn("customer detail entity got deleted");
		;
		return d;
	}

	@Override
	public List<Customer> viewCustomers() throws ResourceNotFoundException {

		List<Customer> f = icr.findAll();
		if (f.isEmpty())
			throw new ResourceNotFoundException("Customers doesnt exist in the database");
		else
			return f;
	}

	@Override
	public Customer viewCustomer(long customerId) throws CustomerDoesntExistsException {
		return icr.findById(customerId)
				.orElseThrow(() -> new CustomerDoesntExistsException("Customer doent exists with id: " + customerId));
	}

	@Override
	public Customer validateCustomer(String username, String password)
			throws CustomerDoesntExistsException, InvalidCredentials {

		Customer up = icr.getCustomerByUsername(username).orElseThrow(
				() -> new CustomerDoesntExistsException("Customer doesnt exists with username: " + username));

		if (up.getUserName().equalsIgnoreCase(username) && up.getPassword().equalsIgnoreCase(password))
			return up;
		else
			throw new InvalidCredentials("Invalid credentidals entered!!1");
	}

}
