package com.example.Online_GasBooking.service.impl;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_GasBooking.entity.Customer;
import com.example.Online_GasBooking.entity.Cylinder;
import com.example.Online_GasBooking.entity.SurrenderCylinder;
import com.example.Online_GasBooking.repo.ICustomerRepository;
import com.example.Online_GasBooking.repo.ICylinderRepository;
import com.example.Online_GasBooking.repo.ISurrenderCylinderRepository;
import com.example.Online_GasBooking.service.ISurrenderCylinderService;

import com.onlinegasbooking.exceptions.ResourceNotFoundException;

@Service
public class SurrenderCylinderImpl implements ISurrenderCylinderService {

	@Autowired
	private ISurrenderCylinderRepository iic;
	@Autowired
	private ICustomerRepository crp;
	@Autowired
	private ICylinderRepository cyl;

	final static Logger logger = org.slf4j.LoggerFactory.getLogger("GasBookingApplication.class");

	@Override
	public SurrenderCylinder insertSurrenderCylinder(SurrenderCylinder sc, long customerId, long cylinderId)
			throws ResourceNotFoundException {

		Customer customer = crp.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Cylinder not found by id :" + sc.getSurrenderId()));

		Cylinder cylinder = cyl.findById(cylinderId)
				.orElseThrow(() -> new ResourceNotFoundException("Cylinder not found by id :" + sc.getSurrenderId()));

		sc.setCustomer(customer);
		sc.setCylinder(cylinder);
		sc.setSurrenderId(sc.getSurrenderId());
		sc.setSurrenderDate(LocalDate.now());

		return iic.save(sc);
	}

	@Override
	public void updateSurrenderCylinder(SurrenderCylinder sc) throws ResourceNotFoundException {
		SurrenderCylinder su1 = iic.findById(sc.getSurrenderId())
				.orElseThrow(() -> new ResourceNotFoundException("Cylinder not found by id :" + sc.getSurrenderId()));
		su1.setCustomer(sc.getCustomer());
		su1.setCylinder(sc.getCylinder());
		su1.setSurrenderId(sc.getSurrenderId());
		su1.setSurrenderDate(sc.getSurrenderDate());
		iic.save(su1);
		logger.info("SurrenderCylinder details successfully updated with surrendercylinderID:" + sc.getSurrenderId());
	}

	@Override
	public void deleteSurrenderCylinder(SurrenderCylinder sc) throws ResourceNotFoundException {
		SurrenderCylinder su2 = iic.findById(sc.getSurrenderId())
				.orElseThrow(() -> new ResourceNotFoundException("Cylinder not found by id :" + sc.getSurrenderId()));
		iic.delete(su2);
		logger.info("SurrenderCylinder details successfully deleted with surrendercylinderID:" + sc.getSurrenderId());

	}

	@Override
	public long countSurrendredCylinders() {
//		List<Long> distinctSurrenderCylinders = scylinderRepository.findAll().stream().map(t -> t.getSurrenderId())
//		.distinct().collect(Collectors.toList());

		logger.info("Counted no of SurrendredCylinders");
		return iic.findAll().size();
	}

}
