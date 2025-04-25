package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Player;
import com.example.demo.entity.Team;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.TeamRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class DatabaseApplicationTests {
    
    @Autowired PlayerRepository playerRepository;
    @Autowired TeamRepository teamRepository;

    @Test
    @Transactional // DB 연결 유지 (원래는 findAll() 메소드 후 연결 종료)
    public void PrintPlayerDtoTest() {
        List<Player> list = playerRepository.findAll();

        list.forEach((player)->{
            System.out.println(player);
        });
    }

    @Test
    @Transactional 
    public void PrintTeamDtoTest() {
        List<Team> list = teamRepository.findAll();

        list.stream().map((team)->{
            System.out.println(team);

            return team;
        }).toList();
    }
}
