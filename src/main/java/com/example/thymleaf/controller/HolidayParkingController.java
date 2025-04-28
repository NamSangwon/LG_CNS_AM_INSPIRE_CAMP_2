package com.example.thymleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.thymleaf.dto.HolidayParkingDto;
import com.example.thymleaf.entity.HolidayParking;
import com.example.thymleaf.repository.HolidayParkingRepository;


@Controller
public class HolidayParkingController {
    @Autowired HolidayParkingRepository holidayParkingRepository;

    @GetMapping("holiday-parking")
    public String getHolidayParking(
        Model model,
        @RequestParam(defaultValue = "1") Integer page
    ) {

        Pageable pageable = PageRequest.of(page - 1, 10);
        
        Page<HolidayParking> list = holidayParkingRepository.findAll(pageable);
        List<HolidayParkingDto> holidayParkingDtoList = list.stream().map(HolidayParking::toDto).toList();

        int prevPage = (page > 1) ? (page - 1) : (page);
        int nextPage = (holidayParkingDtoList.size() < 10) ? (page) : (page + 1);

        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("page", page);
        model.addAttribute("parkings", holidayParkingDtoList);

        return "holiday_parking";
    }
    
}
