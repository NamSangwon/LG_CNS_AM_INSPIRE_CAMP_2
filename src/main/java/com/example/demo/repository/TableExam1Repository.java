package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TableExam1;

// @Repository 필요 X (JpaRepository<>에 이미 명시하였기 때문)
public interface TableExam1Repository 
    extends JpaRepository<TableExam1, Integer> {
    // [메소드 제공]
    // 입력/수정 save()
    // 삭제 delete()
    // 조회 findById() findAll() 
}
