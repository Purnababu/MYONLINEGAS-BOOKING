package com.example.Online_GasBooking.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import com.example.Online_GasBooking.entity.Admin;
import com.example.Online_GasBooking.entity.GasBooking;
import com.onlinegasbooking.exceptions.AdminAlreadyExistsException;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

public interface IAdminService {

	public Admin insertAdmin(Admin admin) throws AdminAlreadyExistsException;

	public Admin updateAdmin(Admin admin) throws ResourceNotFoundException;

	public Admin deleteAdmin(long adminId) throws ResourceNotFoundException;

	public List<GasBooking> getAllBookings(long customerId) throws ResourceNotFoundException;

	public List<GasBooking> getAllBookingsForDays(long customerId, LocalDateTime fromDate, LocalDateTime toDate)
			throws ResourceNotFoundException, ParseException;

}
