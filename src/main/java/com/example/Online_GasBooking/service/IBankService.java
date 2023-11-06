package com.example.Online_GasBooking.service;


import com.example.Online_GasBooking.entity.Bank;
import com.onlinegasbooking.exceptions.BankAlreadyExistsException;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

public interface IBankService {

	public Bank insertBank(Bank bank) throws BankAlreadyExistsException;
	
	public Bank updateBank(Bank bank) throws ResourceNotFoundException;
	
	public Bank deleteBank(Bank bank) throws ResourceNotFoundException;
}
