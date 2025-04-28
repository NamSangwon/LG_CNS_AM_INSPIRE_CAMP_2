package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Major;
import java.util.List;
import java.util.Optional;


public interface MajorRepository 
    extends JpaRepository<Major, Integer> {
    
    // Query Method
    Optional<Major> findById(Integer id);
    
    List<Major> findByName(String name);

    // JPQL
    // 전체 조회
    @Query(value = "select m from Major m")
    List<Major> findAllByJPQL();

    // 이름으로 조회
    @Query(value = """
        select m from Major m
        where m.name = ?1
    """)
    List<Major> findByNameByJPQL(String name);
}
