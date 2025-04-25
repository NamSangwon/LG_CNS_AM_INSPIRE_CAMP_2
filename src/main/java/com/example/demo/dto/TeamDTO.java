package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity를 DTO 클래스로 변환하여 필요한 데이터만 출력하도록 함
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(content = JsonInclude.Include.NON_NULL) // NULL 필드 노출 X
public class TeamDTO {
    int teamId;
    String teamName;
    List<PlayerDTO> players;
}
