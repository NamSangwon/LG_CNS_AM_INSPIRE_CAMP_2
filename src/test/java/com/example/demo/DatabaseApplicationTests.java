package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.PlayerDTO;
import com.example.demo.dto.TeamDTO;
import com.example.demo.entity.Player;
import com.example.demo.entity.Team;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.TeamRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class DatabaseApplicationTests {
    
    @Autowired PlayerRepository playerRepository;
    @Autowired TeamRepository teamRepository;

    // 1. DTO 정보 직접 구성
    @Test
    @Transactional // DB 연결 유지 (원래는 findAll() 메소드 후 연결 종료)
    public void PrintPlayerDtoTest1() {
        List<Player> list = playerRepository.findAll();

        List<PlayerDTO> playerDtoList = list.stream().map((player)->{
            // Create PlayerDTO
            PlayerDTO playerDTO = new PlayerDTO(
                player.getPlayerId(),
                player.getPlayerName(),
                player.getTeam()
            );

            return playerDTO;
        }).toList();

        // print result
        System.out.println(playerDtoList);
    }

    // 2. DTO 변환 메소드 사용하여 구성
    @Test
    @Transactional 
    public void PrintPlayerDtoTest2() {
        List<Player> list = playerRepository.findAll();

        List<PlayerDTO> playerDtoList = list.stream().map(Player::toDto).toList();

        System.out.println(playerDtoList);
    }

    // 1. DTO 정보 직접 구성
    @Test
    @Transactional 
    public void PrintTeamDtoTest1() {
        List<Team> list = teamRepository.findAll();

        List<TeamDTO> teamDtoList = list.stream().map((team)->{
            // Create TeamDTO
            TeamDTO teamDTO = new TeamDTO(
                team.getTeamId(), 
                team.getTeamName(), 
                null
            );
            
            // Append PlayerDTO to TeamDTO's players
            List<PlayerDTO> playerDtoList = team.getPlayers().stream().map((player)->{
                PlayerDTO playerDTO = new PlayerDTO(
                    player.getPlayerId(),
                    player.getPlayerName(),
                    null
                );

                return playerDTO;
            }).toList();
            teamDTO.setPlayers(playerDtoList);

            return teamDTO;
        }).toList();

        // print result
        System.out.println(teamDtoList);
    }    
    
    // 2. DTO 변환 메소드 사용하여 구성
    @Test
    @Transactional 
    public void PrintTeamDtoTest2() {
        List<Team> list = teamRepository.findAll();

        List<TeamDTO> teamDtoList = list.stream().map(Team::toDto).toList();

        System.out.println(teamDtoList);
    }
}
