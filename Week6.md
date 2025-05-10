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
    + `SELECT`, `FROM`, `JOIN`, `ON`, `WHERE`, `GROUP BY`, `HAVING`, `ORDER BY`
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
  + `fetch join`
    + JPQL에서 성능 최적화를 위해 제공하는 기능
    + 연관된 엔티티나 컬렉션을 한 번에 같이 조회
    + 일반 `JOIN`만 사용할 경우, 조인이 이루어 지지 않을 수도 있기 때문에 `fetch join`을 통해 명시
      + ex. `Library`와 `Book`의 연관관계가 `1:N`으로 지정
      + 지연 로딩 전략으로 인해 조회된 `Book`의 개수만큼 추가로 `Library`를 조회하는 쿼리가 나가게 됨
      + &rarr; **`N + 1 문제` 발생**
      + &rarr; **`fetch join`을 통해 해결**
  
  +  **문제점**
    1. JPQL은 문자열(=String) 형태이기 때문에 개발자 의존적 형태
    2. Compile 단계에서 Type-Check가 불가능
    3. RunTime 단계에서 오류 발견 가능 &rarr; 장애 risk 상승
       
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
  + ㅁㄴㅇ
  + ㅁ
---

### 3. Eager / Lazy / Fetch Join
  + ㅁㄴㅇ
  + ㅁ
---

### 4. Native Query
  + ㅁㄴㅇ
  + ㅁ
---

### 5. View Template
  + ㅁㄴㅇ
  + ㅁ
---

### 6. Session
  + ㅁㄴㅇ
  + ㅁ
---

### 7. Cookie
  + ㅁㄴㅇ
  + ㅁ
---
