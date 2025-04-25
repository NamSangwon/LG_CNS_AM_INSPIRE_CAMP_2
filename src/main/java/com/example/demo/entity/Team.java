package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "players")
public class Team {
    @Id
    int teamId;

    String teamName;

    // Player Entity의 @ManyToOne 변수와 매핑 됨을 명시
    // [순환 참조 문제] 및 [Lazy 문제] 등이 발생할 수 있기 떄문에
    // 다음 문제들을 고려하여 사용 여부를 결정하여야 함. (무분별한 사용 X)
    @OneToMany(mappedBy = "team")
    // ,fetch = FetchType.EAGER) // Lazy 문제 해결하기 위해 사용.
    List<Player> players;

    // toString()을 호출 시, player의 toString()을 호출하기 때문에 [순환 참조 문제] 발생
    // [문제 해결 방법]
    // 1. toString() 메소드 오버라이딩
    // 2. @ToString(exclude = "필드명") 사용
}
