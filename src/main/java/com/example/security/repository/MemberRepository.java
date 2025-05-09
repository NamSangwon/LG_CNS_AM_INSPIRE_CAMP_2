package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.entity.Member;

public interface MemberRepository 
    extends JpaRepository<Member, String> {
    
}
