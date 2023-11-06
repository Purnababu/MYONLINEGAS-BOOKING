package com.example.Online_GasBooking.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Online_GasBooking.entity.GasBooking;


@Repository
public interface IGasBookingRepository extends JpaRepository<GasBooking, Long> {

}
