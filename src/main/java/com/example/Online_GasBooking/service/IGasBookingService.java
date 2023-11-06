package com.example.Online_GasBooking.service;


import com.example.Online_GasBooking.entity.GasBooking;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

public interface IGasBookingService {
	
	public GasBooking insertGasBooking(GasBooking gasBooking, long customerId) throws ResourceNotFoundException;
	
	public GasBooking updateGasBooking(GasBooking gasBooking) throws ResourceNotFoundException;
	
	public GasBooking deleteGasBooking(long gasBookingId) throws ResourceNotFoundException;
	
	public float getBill(long customerId) throws ResourceNotFoundException;

}