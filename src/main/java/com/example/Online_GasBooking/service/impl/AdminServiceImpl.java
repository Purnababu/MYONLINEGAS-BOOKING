package com.example.Online_GasBooking.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_GasBooking.entity.Admin;
import com.example.Online_GasBooking.entity.GasBooking;
import com.example.Online_GasBooking.repo.IAdminRepository;
import com.example.Online_GasBooking.repo.ICustomerRepository;
import com.example.Online_GasBooking.service.IAdminService;
import com.onlinegasbooking.exceptions.AdminAlreadyExistsException;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminRepository adp;

	private ICustomerRepository crp;

	@Override
	public Admin insertAdmin(Admin admin) throws AdminAlreadyExistsException {
		Admin save = null;
		Optional<?> a1 = adp.findByName(admin.getUserName());
		if (a1.isPresent()) {
			throw new AdminAlreadyExistsException("exists already");
		} else {
			return save = adp.save(admin);
		}
	}

	@Override
	public Admin updateAdmin(Admin admin) throws ResourceNotFoundException {
		Admin a2 = adp.findById(admin.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found with user id:" + admin.getUserId()));

		a2.setAddress(admin.getAddress());
		a2.setAdminName(admin.getAdminName());
		a2.setEmail(admin.getEmail());
		a2.setMobileNumber(admin.getMobileNumber());
		a2.setPassword(admin.getPassword());
		a2.setUserId(admin.getUserId());
		a2.setUserName(admin.getAdminName());

		adp.save(a2);
		return a2;
	}

	@Override
	public Admin deleteAdmin(long adminId) throws ResourceNotFoundException {
		Admin a3 = adp.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found with user id:" + adminId));
		adp.delete(a3);

		return a3;
	}

	@Override
	public List<GasBooking> getAllBookings(long customerId) throws ResourceNotFoundException {

		com.example.Online_GasBooking.entity.Customer c = crp.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("GasBooking not found for the id :" + customerId));

		return c.getBookings();
	}

	@Override
	public List<GasBooking> getAllBookingsForDays(long customerId, LocalDateTime fromDate, LocalDateTime toDate)
			throws ResourceNotFoundException, ParseException {

		com.example.Online_GasBooking.entity.Customer cust = crp.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found by id:" + customerId));

		List<GasBooking> bookings = cust.getBookings();
		List<GasBooking> list = new ArrayList<GasBooking>();

		for (GasBooking g : bookings) {
			SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");

			Date fd = sd.parse(fromDate.toString());
			Date td = sd.parse(toDate.toString());

			Date rdate = sd.parse(g.getBookingDate().toString());

			if (rdate.compareTo(fd) >= 0 && rdate.compareTo(td) <= 0) {
				list.add(g);
			}
		}
		return list;

	}

}
