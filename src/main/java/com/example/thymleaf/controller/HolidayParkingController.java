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
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "") String search
    ) {

        Pageable pageable = PageRequest.of(page - 1, 10);
        
        Page<HolidayParking> pages = 
            holidayParkingRepository.findByInstitutionContainingOrSidoContainingOrGuContaining(pageable, search, search, search);
        List<HolidayParkingDto> holidayParkingDtoList = pages.stream().map(HolidayParking::toDto).toList();

        int maxPage = pages.getTotalPages();
        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = (startPage + 9 > maxPage) ? (maxPage) : (startPage + 9);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        // 전체 원소 & 전체 페이지 계산 메소드
        model.addAttribute("totalElement", pages.getTotalElements());
        model.addAttribute("totalPage", pages.getTotalPages());

        model.addAttribute("page", page);
        model.addAttribute("parkings", holidayParkingDtoList);

        return "holiday_parking";
    }
    
}
