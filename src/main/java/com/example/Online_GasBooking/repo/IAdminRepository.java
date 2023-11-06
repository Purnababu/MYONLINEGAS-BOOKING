package com.example.Online_GasBooking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Online_GasBooking.entity.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long> {

	@Query(value = "Select a from Admin a where a.userName=?1")
	public Optional<?> findByName(String userName);

}