package com.example.demo.entity;

import com.example.demo.dto.PlayerDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
// toString()을 호출 시, player의 toString()을 호출하기 때문에 [순환 참조 문제] 발생
// [문제 해결 방법]
// 1. toString() 메소드 오버라이딩
// 2. @ToString(exclude = "필드명") 사용
@ToString(exclude = "team")
public class Player {
    @Id
    int playerId;

    String playerName;

    @ManyToOne
    @JoinColumn(name="team_id")
    Team team;

    public PlayerDTO toDto() {
        return new PlayerDTO(playerId, playerName, team);
    }
}
