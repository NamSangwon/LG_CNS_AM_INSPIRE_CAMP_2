package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Location;

public interface LocationRepository 
    extends JpaRepository<Location, Long> {

    // Native Query Practice
    // (lat, lng) 위치에서 radius 이내의 location 조회
    @Query(value = """
            SELECT id, title, lat, lng,
                (6371 * acos(
                    cos(radians(:lat)) * cos(radians(lat)) *
                    cos(radians(lng) - radians(:lng)) +
                    sin(radians(:lat)) * sin(radians(lat))
                )) AS distance
            FROM point
            HAVING distance < :radius
            ORDER BY distance LIMIT 20
            """, nativeQuery = true)
    List<Object[]> findNearby(
        @Param("lat") double lat,
        @Param("lng") double lng, 
        @Param("radius") double radius
    );
}
