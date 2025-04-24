package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.AssemblyMember;
import com.example.demo.repository.AssemblyMemberRepository;

// JUnit Test
@SpringBootTest
class DemoApplicationTests {

	@Autowired AssemblyMemberRepository assemblyMemberRepository;

	// [데이터 1 건 조회] 테스트
	@Test
	void contextLoads() {
		// Null Pointer Exception 오류를 피하기 위해 Optional<T> 클래스를 사용
		Optional<AssemblyMember> opt = assemblyMemberRepository.findById(1);
		
		AssemblyMember member = new AssemblyMember();
		if (opt.isPresent())
		{
			member = opt.get();
		}

		// (In VSC) DEBUG CONSOLE > [Launch Java Tests - ...]에 출력
		System.out.println(member);

		// 조회한 결과가 맞는 지, 확인하는 코드 (assertXXX() 메소드)
		// 조회한 데이터의 id 가 1인지 확인하여, 조회 성공 여부 확인
		int id = member.getId();
		assertEquals(1, id);
	}

	// 여러 개 가능
	@Test
	void OtherContextLoads() {
	}

}
