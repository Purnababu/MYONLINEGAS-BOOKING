package com.example.Online_GasBooking.controller;

import java.time.LocalDateTime;
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

import com.example.Online_GasBooking.entity.Admin;
import com.example.Online_GasBooking.entity.GasBooking;
import com.example.Online_GasBooking.service.IAdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@PostMapping("/add-admin")
	public ResponseEntity<Admin> insertAdmin(@RequestBody Admin admin) throws Exception {
		if (admin == null)
			throw new Exception("Admin Object is null");
		else
			return new ResponseEntity<Admin>(adminService.insertAdmin(admin), HttpStatus.CREATED);
	}

	@PutMapping("/update-admin")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) throws Exception {
		if (admin == null)
			throw new Exception("Admin Object is null");
		else
			return new ResponseEntity<Admin>(adminService.updateAdmin(admin), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete-admin/{adminId}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable long adminId) throws Exception {
		if (adminId == 0)
			throw new Exception("AdminId can not be 0");
		else
			return new ResponseEntity<Admin>(adminService.deleteAdmin(adminId), HttpStatus.CREATED);
	}

	@GetMapping("/all-bookings-admin/{customerId}")
	public ResponseEntity<List<GasBooking>> getAllBookings(@PathVariable long customerId) throws Exception {
		if (customerId == 0)
			throw new Exception("CustomerId can not be 0");
		else
			return new ResponseEntity<List<GasBooking>>(adminService.getAllBookings(customerId), HttpStatus.CREATED);
	}

	@GetMapping("/Bookings-fordays-admin/{customerId}/{fromDate}/{toDate}")
	public ResponseEntity<List<GasBooking>> getAllBookingsForDays(@PathVariable long customerId, LocalDateTime fromDate,
			LocalDateTime toDate) throws Exception {
		if (customerId == 0 && fromDate == null && toDate == null) {
			throw new Exception("Parameters must not be null");
		} else
			return new ResponseEntity<List<GasBooking>>(
					adminService.getAllBookingsForDays(customerId, fromDate, toDate), HttpStatus.CREATED);
	}

}
