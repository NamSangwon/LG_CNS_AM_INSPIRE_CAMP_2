package com.example.thymleaf.entity;

import com.example.thymleaf.dto.HolidayParkingDto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "holiday_parking")
@Data
public class HolidayParking {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(length = 30, nullable = false)
   private String institution;  // 기관명

   @Column(length = 30)
   private String manager;  // 담당자

   @Column(length = 40)
   private String tel;  // 연락처

   @Column(length = 10, nullable = false)
   private String sido;  // 주차장시도

   @Column(length = 10)
   private String gu;  // 주차장구군

   @Column(length = 80, nullable = false)
   private String address;  // 주소

   @Column(length = 50)
   private String name;  // 주차장명

   @Column(length = 80)
   private String loc;  // 위치정보

   @Column(length = 20)
   private String type;  // 주차장유형

   @Column(length = 20)
   private String lng;  // 경도

   @Column(length = 20)
   private String lat;  // 위도

   @Column(length = 4000)
   private String etc;  // 참고사항

   public HolidayParkingDto toDto() {
      return new HolidayParkingDto(
         id, sido, gu, institution, manager, tel, address
      );
   }
}
