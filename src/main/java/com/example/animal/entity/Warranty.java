package com.example.animal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Warranty {
  @Id
  @GeneratedValue
  Long id;

  @Column(length = 50)
  String modelNm;

  @Column(nullable = true)
  Integer period;
}
