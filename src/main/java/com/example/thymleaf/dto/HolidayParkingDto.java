package com.example.thymleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HolidayParkingDto {
    
   private Integer id;

   private String sido;  // 주차장시도

   private String gu;  // 주차장구군

   private String institution;  // 기관명

   private String manager;  // 담당자

   private String tel;  // 연락처

   private String address;  // 주소
}
