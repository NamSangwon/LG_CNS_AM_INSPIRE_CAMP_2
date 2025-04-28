package com.example.thymleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.thymleaf.dto.HolidayParkingDto;
import com.example.thymleaf.entity.HolidayParking;

public interface HolidayParkingRepository 
    extends JpaRepository<HolidayParking, Integer> {
        
}
