package com.example.Online_GasBooking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Online_GasBooking.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

	@Query("Select c from Customer c where c.userName=?1")
	public Optional<Customer> getCustomerByUsername(String userName);
}
