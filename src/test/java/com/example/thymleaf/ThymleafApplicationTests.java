package com.example.thymleaf;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.thymleaf.dto.HolidayParkingDto;
import com.example.thymleaf.entity.HolidayParking;
import com.example.thymleaf.repository.HolidayParkingRepository;

@SpringBootTest
class ThymleafApplicationTests {

	@Autowired HolidayParkingRepository holidayParkingRepository;

	@Test
	void contextLoads() {
		Pageable pageable = PageRequest.of(0, 10);
        
        Page<HolidayParking> list = holidayParkingRepository.findAll(pageable);
        list.forEach((holidayParking)->{
			HolidayParkingDto dto = holidayParking.toDto();
			System.out.println(dto);
		});

        
	}

}
