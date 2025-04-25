package com.example.animal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Product {
  @Id
  @GeneratedValue
  Long id;

  @Column(length = 50)
  String name;

  @Column(nullable = true)
  Integer price;

  @ManyToOne
  Animal animal;

  @OneToOne
  Warranty warranty;
}
