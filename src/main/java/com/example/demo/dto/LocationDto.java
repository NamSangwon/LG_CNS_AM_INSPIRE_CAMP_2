package com.example.demo.dto;

import lombok.Data;

@Data
public class LocationDto {
    Long id;
    String title;
    Double lat;
    Double lng;
    Double distance;
}
