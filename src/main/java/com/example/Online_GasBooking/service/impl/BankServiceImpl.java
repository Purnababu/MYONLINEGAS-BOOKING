package com.example.Online_GasBooking.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_GasBooking.entity.Bank;
import com.example.Online_GasBooking.repo.IBankRepository;
import com.example.Online_GasBooking.service.IBankService;
import com.onlinegasbooking.exceptions.BankAlreadyExistsException;
import com.onlinegasbooking.exceptions.ResourceNotFoundException;

@Service
public class BankServiceImpl implements IBankService {

	@Autowired
	IBankRepository ibr;

	final static Logger logger = org.slf4j.LoggerFactory.getLogger("GasBookingApplication.class");

	@Override
	public Bank insertBank(Bank bank) throws BankAlreadyExistsException {

		Bank b1 = null;
		Optional<Bank> b = ibr.findBankByName(bank.getBankName());

		if (!b.isEmpty()) {
			if (b.get().getBankName().equalsIgnoreCase(bank.getBankName()))
				logger.info("Bank already exists");
			logger.warn("Insertion failure!!!!!!!!!!!");
			throw new BankAlreadyExistsException("Bank Already exists By Name :" + bank.getBankName());

		} else {
			logger.info("Bank details added.");
			b1 = ibr.save(bank);
		}

		return b1;
	}

	@Override
	public Bank updateBank(Bank bank) throws ResourceNotFoundException {

		Bank b2 = ibr.findById(bank.getBankId())
				.orElseThrow(() -> new ResourceNotFoundException("Bank doesnt exist with id:" + bank.getBankId()));
		b2.setBankId(bank.getBankId());
		b2.setBankName(bank.getBankName());
		b2.setAddress(bank.getAddress());

		ibr.save(b2);
		return b2;
	}

	@Override
	public Bank deleteBank(Bank bank) throws ResourceNotFoundException {
		Bank b3 = ibr.findById(bank.getBankId())
				.orElseThrow(() -> new ResourceNotFoundException("Bank doesnt exist with id:" + bank.getBankId()));

		ibr.delete(b3);
		return b3;

	}

}
