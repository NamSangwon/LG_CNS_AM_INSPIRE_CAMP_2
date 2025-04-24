package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "table_exam_2")
public class TableExam2 {
    @Id
    Integer id;

    String name;

    // 세계 표준 시 UTC (Date) <-> 현지 시각 (LocalDate/LocalTime/LocalDateTime 시용)
    // But, JPA에서 현지 시각으로 변경해 줌
    @Temporal(TemporalType.DATE)
    Date birthDay;

    @Temporal(TemporalType.TIME)
    Date birthTime;

    @Temporal(TemporalType.TIMESTAMP)
    Date signupDate;
}