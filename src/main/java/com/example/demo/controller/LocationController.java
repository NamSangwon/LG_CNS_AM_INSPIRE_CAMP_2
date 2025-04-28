package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.LocationDto;
import com.example.demo.repository.LocationRepository;

@Controller
public class LocationController {
    @Autowired LocationRepository locationRepository;

    // 입력 받은 위치에서 25km 이내의 location 조회
    @GetMapping("/location")
    @ResponseBody
    public List<LocationDto> getNearbyLocations(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "25") double radius) {

        return locationRepository.findNearby(lat, lng, radius)
                .stream()
                .map(row -> {
                    LocationDto dto = new LocationDto();
                    dto.setId((Long) row[0]);
                    dto.setTitle((String) row[1]);
                    dto.setLat((Double) row[2]);
                    dto.setLng((Double) row[3]);
                    dto.setDistance((Double) row[4]);
                    return dto;
                })
                .toList();
    }

}
