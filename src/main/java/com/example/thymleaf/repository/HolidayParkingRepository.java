package com.example.thymleaf.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.thymleaf.dto.HolidayParkingDto;
import com.example.thymleaf.entity.HolidayParking;

public interface HolidayParkingRepository 
    extends JpaRepository<HolidayParking, Integer> {
        
    // institution or sido or gu에 검색어가 포함되는 데이터 조회
    Page<HolidayParking> findByInstitutionContainingOrSidoContainingOrGuContaining(Pageable pageable, String ins, String sido, String gu);

}
