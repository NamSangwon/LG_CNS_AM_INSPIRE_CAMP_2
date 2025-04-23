package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Demo;

@Mapper
public interface DemoMapper {
    public List<Map<String, Object>> select1();
    public List<Demo> select2();

    public Integer insert1(int seq, String user);
    public Integer insert2(Demo demo);

    public Integer delete1(int seq);
    public Integer delete2(Demo demo);
}
