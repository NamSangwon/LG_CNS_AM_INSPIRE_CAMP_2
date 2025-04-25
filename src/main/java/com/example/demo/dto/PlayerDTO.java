package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.demo.entity.Team;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;

// Entity를 DTO 클래스로 변환하여 필요한 데이터만 출력하도록 함
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(content = JsonInclude.Include.NON_NULL) // NULL 필드 노출 X
public class PlayerDTO {
    int playerId;
    String playerName;
    Team team;
}
