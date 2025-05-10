# 6주차 학습 내용
#### &rarr; `DB Query`, `View Template`, `Session & Cookie`
---
### 1. `JPQL`
  + Java Persitence Query Language
  + JPA에서 사용되는 객체지향 쿼리 언어
  + DB의 테이블이 아닌 Entity 클래스를 기반으로 동작 &rarr; **DB에 독립적**
    + 따라서, `Query Method`와 동일하게 JPA가 해석하는 과정을 거쳐야 함
    + `Query Method`보다 좋은 성능을 위해 사용 (ex. `GROUP BY`, `N + 1 문제` 등)
  + 기본 구조
    + `SELECT`+`FROM`+`JOIN`+`ON`+`WHERE`+`GROUP BY`+`HAVING`+`ORDER BY`
    + 사용 예시
      ```java
        // 집계 함수 사용 예
        @Query(value="select t.survived, count(1) from Titanic as t group by t.survived")
        List<Object[]> countBySurvived();

        // 변수 사용 예 1 (순서 기반)
        @Query(value="select t from Titanic as t where t.age between ?1 and ?2")
        List<Titanic> findByAgeBetween(Double begin, Double end);

        // 변수 사용 예2 (변수 지정)
        @Query(value = "select u from User u where u.name = :name")
        List<User> findByName(@Param("name") String name);
      ```
  + **`fetch join`**
    + JPQL에서 성능 최적화를 위해 제공하는 기능
    + 연관된 엔티티나 컬렉션을 한 번에 같이 조회
    + 일반 `JOIN`만 사용할 경우, 조인이 이루어 지지 않을 수도 있기 때문에 `fetch join`을 통해 명시
      + ex. `Library`와 `Book`의 연관관계가 `1:N`으로 지정
      + 지연 로딩 전략으로 인해 조회된 `Book`의 개수만큼 추가로 `Library`를 조회하는 쿼리가 나가게 됨
      + &rarr; **`N + 1 문제` 발생**
      + &rarr; **`fetch join`을 통해 해결**
  
  + **문제점**
    + JPQL은 문자열(=String) 형태이기 때문에 개발자 의존적 형태
    + Compile 단계에서 Type-Check가 불가능
    + RunTime 단계에서 오류 발견 가능 &rarr; 장애 risk 상승
       
  + **`Query DSL`**
    + `JPQL`의 문제점을 보완
    + 정적 타입을 이용해서 SQL, JPQL을 코드로 작성할 수 있도록 도와주는 오픈소스 빌더 API
    + **장단점**
      + 코드 라인이 늘어난다
      + 문자가 아닌 코드로 작성
      + Compile 단계에서 문법 오류를 확인 가능
      + 코드 자동 완성 기능 활용 가능
      + 동적 쿼리 구현 가능
    + 사용 예시
      ```java
        @PersistenceContext
        EntityManager em;
         
        public List<Person> selectPersonByNm(String firstNm, String lastNm){
        	JPAQueryFactory jqf = new JPAQueryFactory(em);
        	QPerson person = QPerson.person;
          
        	List<Person> personList = jpf
                                      .selectFrom(person)
                                      .where(person.firstName.eq(firstNm)
                                        .and(person.lastName.eq(lastNm))
                                      .fetch();
                                        
        	return personList;
        }
      ```
---

### 2. Cascade
  + 영속성 전이 : Entity 간의 연관관계에서 부모 Entity의 상태 변화를 자식 Entity로 전파
    
  + **Entity 상태 종류**
    + `Transient` (비영속)
      + 영속성 컨텍스트와 전혀 관계가 없는 상태
      + 객체를 생성만 한 상태
    + `Persistent` (영속)
      + 영속성 컨텍스트에 등록된 상태 (`entityManager.persist()`를 통해 등록)
      + Entity가 영속성 컨텍스트에 의해 관리되는 상태
        + 영속 상태가 된다고 바로 DB에 적용 X
        + 트랜잭션의 COMMIT 시점에 영속성 컨테스트에 있는 정보들이 DB에 적용
    + `Detached` (준영속)
      + 영속 상태의 엔티티가 영속성 컨텍스트에서 분리된 상태
      + 영속성 컨텍스트가 제공하는 기능 사용 불가 (ex. 지연로딩, 1차 캐시, 쓰기 지연, 변경 감지)
      + 식별자 값을 가지고 있음 (영속 상태였다가 분리된 상태이므로)
      + 비영속 상태에 가까움
    + `Removed` (삭제) : 삭제된 상태
      
  + **Cascade 종류**
    + `CascadeType.ALL` : 모든 CASCADE 적용
    + `CascadeType.PERSIST` : 엔티티를 영속화할 때, 연관된 엔티티도 함께 유지
    + `CascadeType.MERGE` : 엔티티 상태를 병합할 때, 연관된 엔티티도 모두 병합
    + `CascadeType.REMOVE` : 엔티티를 제거할 때, 연관된 엔티티도 모두 제거
    + `CascadeType.DETACH` : 엔티티를 준영속화할 때, 연관된 엔티티도 모두 준영속화. (변경 사항 반영 X)
    + `CascadeType.REFRESH` : 엔티티를 새로고침할 때, 연관된 엔티티도 모두 새로고침
   
  + 사용 예제
    ```java
        @Test
        @DisplayName("연관관계를 가진 두 엔티티에 CASCADE 속성을 설정하지 않으면 예외가 발생")
        void insertBoard() {
            IntStream.rangeClosed(301, 350)
                    .forEach(i -> {
                        // memberRepository에 save()하지 않고, 생성만 하였으므로 member는 비영속 상태 
                        Member member = Member.builder().email("user" + i + "@aaa.com").build();
                        Board board = Board.builder()
                                .title("Title..." + i)
                                .content("Content..." + i)
                                .member(member)
                                .build();

                        // 따라서, board를 저장 시, 영속 상태가 아닌 member를 포함하므로 에러 발생
                        // CascadeType.ALL 이나 Cascade.PERSIST 옵션을 부여하여, 영속성 전이
                        // ex. Board 내의 Member 변수에 @ManyToOne(cascade = CascadeType.PERSIST) 옵션 부여
                        boardRepository.save(board);
                    });
        }
    ```

### 3. Native Query
  + JPA에서 DB에 직접 SQL을 실행시키는 방법
  + 복잡한 쿼리, DB 함수, 성능 최적화가 필요할 때, 유용하게 사용
  + 사용 예시
    ```java
      public interface LocationRepository extends JpaRepository<Location, Long> {
       @Query(value = """
           SELECT id, title, lat, lng,
               (6371 * acos(
                   cos(radians(:lat)) * cos(radians(lat)) *
                   cos(radians(lng) - radians(:lng)) +
                   sin(radians(:lat)) * sin(radians(lat))
               )) AS distance
           FROM point
           HAVING distance < :radius
           ORDER BY distance LIMIT 20
           """, nativeQuery = true)
       List<Object[]> findNearby(
           @Param("lat") double lat, @Param("lng") double lng, @Param("radius") double radius);
      }
    ```
  + **문제점**
    + 코드가 DB에 대한 종속성 증가
    + SQL Injection 공격의 위험성 증가
    + 따라서, 필요한 경우에만 사용하고, 쿼리에 사용된 데이터의 유효성 검사 必
---

### 4. View Template
  + **`Thymeleaf`**
    + a
      
  + `JSP`
    + 
---

### 5. Session
  + ㅁㄴㅇ
  + ㅁ
---

### 6. Cookie
  + ㅁㄴㅇ
  + ㅁ
---
