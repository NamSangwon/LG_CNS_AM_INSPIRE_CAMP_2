package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Major {
    @Id
    int id;

    @Column(length = 255)
    String name;

    @Column(nullable = false)
    int maxPrsn;

    @Temporal(TemporalType.DATE)
    Date ebtbDate;
}
