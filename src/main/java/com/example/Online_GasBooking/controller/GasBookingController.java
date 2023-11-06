package com.example.Online_GasBooking.controller;

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

import com.example.Online_GasBooking.entity.GasBooking;
import com.example.Online_GasBooking.service.IGasBookingService;

@RestController
@RequestMapping("/api/gas-booking-controller")
public class GasBookingController {

	@Autowired
	private IGasBookingService bookingService;

	@PostMapping("/add-booking/{customerId}")
	public ResponseEntity<GasBooking> insertGasBooking(@RequestBody GasBooking gasBooking,
			@PathVariable long customerId) throws Exception {
		if (gasBooking == null || customerId == 0)
			throw new Exception("Credentials Can not be empty");
		else
			return new ResponseEntity<GasBooking>(bookingService.insertGasBooking(gasBooking, customerId),
					HttpStatus.CREATED);
	}

	@PutMapping("/update-gasbooking")
	public ResponseEntity<GasBooking> updateGasBooking(@RequestBody GasBooking gasBooking) throws Exception {
		if (gasBooking == null)
			throw new Exception("Object is Empty");
		else
			return new ResponseEntity<GasBooking>(bookingService.updateGasBooking(gasBooking), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete-gasbooking/{gasBookingId}")
	public ResponseEntity<GasBooking> deleteGasBooking(@PathVariable long gasBookingId) throws Exception {
		if (gasBookingId == 0)
			throw new Exception("GasBookingId can not be empty");
		else
			return new ResponseEntity<GasBooking>(bookingService.deleteGasBooking(gasBookingId), HttpStatus.CREATED);
	}

	@GetMapping("/get-bill/{customerId}")
	public ResponseEntity<?> getBill(@PathVariable long customerId) throws Exception {

		if (customerId == 0)
			throw new Exception("CustomerId can not be empty");
		else
			return new ResponseEntity<>(bookingService.getBill(customerId), HttpStatus.CREATED);
	}
}
