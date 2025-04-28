package com.example.animal.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Playground {
  @Id
  @GeneratedValue
  Long id;

  @Column(length = 50)
  String name;
  
  @Column(length = 50)
  String address;

  @Column(length = 20)
  String tel;

  @OneToMany(mappedBy = "playground")
  List<PlaygroundAnimals> PlaygroundAnimals;
}
