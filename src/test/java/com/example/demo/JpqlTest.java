package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Major;
import com.example.demo.repository.MajorRepository;

@SpringBootTest
public class JpqlTest {
    @Autowired MajorRepository majorRepository;

    @Test
    void test() {
        // 전체 조회 후 출력
        List<Major> list = majorRepository.findAllByJPQL();
        System.out.println(list);

        // 이름으로 조회 후 출력
        List<Major> list2 = majorRepository.findByNameByJPQL("IT");
        System.out.println(list2);
    }
}
