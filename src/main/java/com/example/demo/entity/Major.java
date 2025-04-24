package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    Integer id;

    @Column(length = 255, nullable = false)
    String name;

    @Column(nullable = false)
    int maxPrsn;

    @Temporal(TemporalType.DATE)
    Date ebtbDate;
}
