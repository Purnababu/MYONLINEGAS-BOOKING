package com.example.Online_GasBooking.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_GasBooking.entity.Customer;
import com.example.Online_GasBooking.entity.GasBooking;
import com.example.Online_GasBooking.repo.ICustomerRepository;
import com.example.Online_GasBooking.repo.IGasBookingRepository;
import com.example.Online_GasBooking.service.IGasBookingService;

import com.onlinegasbooking.exceptions.ResourceNotFoundException;

@Service
public class GasBookingServiceImpl implements IGasBookingService {

	@Autowired
	private IGasBookingRepository br;

	@Autowired
	private ICustomerRepository cr;

	@Override
	public GasBooking insertGasBooking(GasBooking gasBooking, long customerId) throws ResourceNotFoundException {

		Customer cu = cr.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found by id :" + customerId));

		gasBooking.setCustomer(cu);
		gasBooking.setBookingDate(LocalDate.now());

		return br.save(gasBooking);
	}

	public GasBooking updateGasBooking(GasBooking gasBooking) throws ResourceNotFoundException {

		GasBooking gb = br.findById(gasBooking.getGasBookingId()).orElseThrow(
				() -> new ResourceNotFoundException("Customer not found by id:" + gasBooking.getGasBookingId()));

		gb.setBill(gasBooking.getBill());
		gb.setGasBookingId(gasBooking.getGasBookingId());
		gb.setStatus(gasBooking.isStatus());

		return gb;
	}

	@Override
	public GasBooking deleteGasBooking(long gasBookingId) throws ResourceNotFoundException {

		GasBooking gbd = br.findById(gasBookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found by id:" + gasBookingId));

		br.delete(gbd);
		return gbd;
	}

	@Override
	public float getBill(long customerId) throws ResourceNotFoundException {

		Customer cu = cr.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found by id :" + customerId));

		float bill = 0;

		for (GasBooking gb : cu.getBookings()) {
			bill += gb.getBill();
		}
		return bill;
	}

}
