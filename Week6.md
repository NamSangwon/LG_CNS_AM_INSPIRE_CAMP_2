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
    + HTML5 문법을 사용하는 HTML 태그 및 속성 기반의 Template Engine
    + 스프링부트에서 권장하는 View Template
    + 텍스트, HTML, XML, JavaScript, CSS 등 사용 가능
    + Controller에서 Model을 통해 데이터를 넘기고, View에서 받은 데이터를 출력
   
    + `${...}` : Variable Expression
      ```html
        아이디:<span>[[${user.userId}]]</span><br>
        이름:<span>[[${user.userName}]]님</span><br>
        나이:<span>[[${user.userAge}]]세</span><br>
        <hr>
        아이디:<span th:text="${user.userId}"></span><br>
        이름:<span th:text="${user.userName}+ 님"></span><br>
        나이:<span th:text="${user.userAge}+ 세"></span><br>
        <hr>
        아이디:<span data-th-text="${user.userId}"></span><br>
        이름:<span data-th-text="@{|${user.userName}님|}"></span><br>
        나이:<span data-th-text="@{|${user.userAge}세|}"></span><br>
      ```
      
    + `th:each` : Iteration
      ```html
        <th:block th:each="pageNumber : ${#numbers.sequence(1, 10)}">
          <span th:text="${pageNumber}"></span>
        </th:block>
      ```
      
    + `th:if`, `th:unless`, `th:switch` : Condition Evaluation
      ```html
        관리자 이름 :
        <span th:if="${name} != null" th:text="${name}"></span>
        <span th:unless="${name} != null" th:text="이름없음"></span>

        <!-- 삼항연산자를 통한 출력 값 결정 -->
        <br> 권한 : <span th:text="${auth} != null ? ${auth} : '권한없음'"></span>
      
        <br> 담당 카테고리 :
        <span th:switch="${category}">
          <span th:case="1">커뮤니티</span>
          <span th:case="2">장터</span>
          <span th:case="3">갤러리</span>
        </span>
      ```
      
    + `@{ ... }` : Link Url Expression
      ```html
        <!-- 1 ~ 10까지의 페이지 (현재 페이지는 span 태그 & 다른 페이지는 a 태그) -->
        <th:block th:each="pageNumber : ${#numbers.sequence(1, 10)}">
          <span th:if="${page} == ${pageNumber}" th:text="${pageNumber}"></span>
          <!-- "/linkUrl?page=?"으로의 링크 -->
          <a th:unless="${page} == ${pageNumber}" th:href="@{/linkUrl(page=${pageNumber})}"th:text="${pageNumber}"></a>
        </th:block>
      ```
      
    + Template Layout
      + 공통으로 사용될 전체/일부 모습을 미리 작성
        + 공통된 전체 모습은 thymeleaf-layout-dialect라는 3rd-party-library 사용 必
          ```java
            // layout1.html 파일 출력
            @GetMapping("layout1")
              public String layout1() {
              return "layout1";
            }
            // layout2.html 파일 출력
            @GetMapping("layout2")
              public String layout2() {
              return "layout2";
            }
          ```
          ```html
            <!-- 바뀔 부분 불러오기 -->
            <th:block layout:fragment="content"></th:block>
          ```
          ```html
            <!-- layout1.html -->
            <!-- common/layout.html 파일을 불러오기  -->
            <html layout:decorate="~{common/layout}">
              <!-- common/layout.html 파일의 "content"라는 layout:fragment 채우기 -->
              <div class="container-fluid mt-3" layout:fragment="content">
                <h3>1번화면</h3>
                <img src="이미지 주소 링크" style="width: 100%">
              </div>
            </html>
          ```
          
        + 공통된 일부 모습은 thymeleaf의 공식 기능
          ```java
            // insert1.html 파일 출력
            @GetMapping("insert1")
              public String insert1() {
              return "insert1";
            }
          ```
          ```html
            <!-- header.html -->
            <!-- 공통으로 사용될 일부 모습 -->
            <h2>Accordion Example</h2>
          ```
          ```html
            <!-- insert1.html -->
            <!-- 공통으로 사용될 일부 모습 불러와서 적용 -->
            <header th:insert="~{common/header}">  <!-- header 태그 안에 삽입 -->
            <header th:replace="~{common/header}"> <!-- header 태그를 교체 -->
          ```
    + Escape / Unescape
      + `th:text` : Escape
        + 특정 문자(예: <, >, &, ", ')를 HTML 엔티티로 변환하여 출력하는 방식
        + 자바스크립트 주입 공격(XSS)을 방지하기 위해 사용
      + `th:utext` : Unescape
        + 특별한 변환없이 원래의 문자를 그대로 출력하는 방식
        + HTML 태그나 스크립트 코드가 브라우저에 그대로 렌더링
          + 따라서, 자바스크립트 주입 공격(XSS)에 노출됨
        + 사용 예
          + 관리자가 직접 작성한 HTML 콘텐츠를 출력할 때
          + 서버에서 동적으로 만든 스크립트를 그대로 출력할 때
          + 외부 에디터(예: CKEditor, TinyMCE)로 작성된 HTML 콘텐츠를 보여줄 때
      +  사용자 입력은 `th:text` 사용 & 관리자나 확인한 입력은 `th:utext` 사용
      
  + `JSP`
    + Java Server Pages
    + 값 출력과 간단한 제어문 외에도 자바 코드를 직접 사용할 수 있는 기술 (레거시 시스템)
      ```html
        <%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <body>
          아이디:<span>${user.userId}</span><br>
          이름:<span>${user.userName}</span><br>
          나이:<span>${user.userAge}</span><br>
        </body>
      ```
---

### 5. Session
  + 특징
    + 클라이언트에 대한 정보를 서버에 저장할 수 있는 공간
    + 클라이언트가 서버에 접속 후 세션에 접근할 때 공간 생성
    + 고유한 식별자를 가지며, 클라이언트는 이 식별자를 통해 세션을 유지 (식별자는 클라이언트의 쿠키에 저장)
    + 설정된 만료시간 이내에 클라이언트의 요청이 없으면 세션 삭제 (Tomcat의 기본 만료시간은 30분)
    + 세션을 분실하는 경우 새로운 세션을 생성하고 다시 클라이언트로 전송
    + 클라이언트가 접속되어 있는 동안 내용을 기억해야 하는 경우에 활용
  + 사용 예시
    ```java
      @GetMapping("/login")
        public String login() {
        return "login";
      }
      @PostMapping("/login")
      public String loginPost(
        @ModelAttribute User user,
        HttpSession session
      ) {
        // 세션에 "user"라는 이름으로 user 데이터 저장
        session.setAttribute("user", user);
        return "redirect:/main";
      }
      @GetMapping("logout")
      public String logout(HttpSession session) {
        // 세션 값 제거
        session.invalidate();

        return "redirect:/login";
      }
      @GetMapping("/main")
      public String main() {
        return "main";
      }
    ```
    ```html
      <!-- session.user을 통해 세션에 "user"라는 이름으로 저장된 값에 접근 -->
      <p th:if="${session.user} != null" th:text="${session.user.userId} + '님 접속 성공'"></p>
      <p th:unless="${session.user} != null">
        로그인되어 있지 않습니다.
      </p>
    ```
---

### 6. Cookie
  + ㅁㄴㅇ
  + ㅁ
---
