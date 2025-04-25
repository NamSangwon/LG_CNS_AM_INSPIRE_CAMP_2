package com.example.animal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PlaygroundAnimals {
  @Id
  @GeneratedValue
  Long id;

  @ManyToOne
  @JoinColumn(nullable = false)
  Playground playground;

  @ManyToOne
  @JoinColumn(nullable = false)
  Animal animal;

}
