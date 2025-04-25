package com.example.animal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Animal {
  @Id
  @GeneratedValue
  Long id;

  @Column(length = 50)
  String name;

  @Column(nullable = true)
  Integer age;

  @ManyToOne
  Owner owner;
}
