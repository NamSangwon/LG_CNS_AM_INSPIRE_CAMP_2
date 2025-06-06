# 5주차 학습 내용 - Spring

### 1. Spring
  + **Spring 동작 흐름**
    ![Spring 동작 흐름](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FDQ1tN%2Fbtr7gz3jzX7%2FxQYXXXZYNvs4iN02Uwt6lk%2Fimg.png)
    + `스프링 컨테이너`라고 불리는 스프링 런타임 엔진 제공
    + 설정 정보를 참고하여 애플리케이션을 구성하는 오브젝트를 생성 및 관리
  
  + **특징**
    + `경량 컨테이너`로서 자바 객체를 직접 관리
      + 객체 생성, 소멸과 같은 라이프 사이클을 관리하며 스프링으로부터 필요한 객체를 얻어 옴
    + `POJO 프로그래밍`을 지향
      + `POJO` : 객체지향적인 원리에 충실하면서, 특정 환경과 규약에 종속되지 않는 오브젝트
        + 특정 인터페이스/클래스를 상속하지 않음
        + 환경과 기술에 종속적 X &rarr; 재사용성↑ & 확장 가능성↑ (유연한 코드)
        + 저수준 레벨의 기술과 환경에 종속적인 코드 X &rarr; 깔끔한 코드 작성 &rarr; 쉬운 디버깅
        + 특정 기술이나 환경에 종속적 X &rarr; 단순한 테스트
        + **객체 지향적인 설계를 제한없이 적용**
    + `IoC/DI`, `AOP`, `PSA` 지원
      + Spring의 3가지 핵심 개념을 통해 `POJO 프로그래밍` 작성 지향
      + `IoC/DI`
        + `IoC` : 제어의 역전
          + 애플리케이션 흐름의 주도권이 뒤바뀜
          + 객체의 생성부터 생명주기를 스프링 컨테이너가 관리
        + `DI` : 의존성 주입
          + 객체를 직접 생성 하지 않고, 외부에서 객체를 생성한 후 주입
          + 개방 폐쇄 원칙(OCP)의 이점 : 확장에는 열려있고, 변경에는 닫혀있어 재사용 가능
          + [ 3가지 방법 ]
            1. Setter Injection : `Setter 메소드` 주입
            2. Field Injection : 필드에 `@Autowired` 작성
            3. Constructor Injection : `생성자`를 통해 의존성 주입
        + 객체(Bean) 생성 &rarr; 제어권을 지닌 스프링이 만들어 놓은 객체를 주입 &rarr; 의존성 객체 메소드 호
      + `AOP` : 관점 지향 프로그래밍
        + 핵심 기능(핵심 관점) 과 부가 기능(공통 기능/부가 관점)으로 나눈 후 각각을 모듈화하고 코드의 외부에서 삽입하는 기술
        + 즉, 공통된 기능을 재사용하는 기법 (공통된 기능의 예 : 트랜잭션, 로깅, 보안 등)
        + 이점 : 코드의 간결성 유지, 객체 지향 설계 원칙에 맞는 코드 구현, 코드 재사용성
      + `PSA` : 하나의 추상화로 여러 서비스를 묶어둔 것
        + 환경의 변화와 관계없이 일관된 방식으로 기술에 접근할 수 있는 환경을 제공하는 추상화 구조
        + 서비스 추상화로 제공되는 기술을 다른 기술 스택으로 간편하게 바꿀 수 있는 확장성을 갖고 있음
        + ex. Spring Web MVC, Spring Transaction Spring Cache
    + **확장성이 높아 유지 보수하기 용이함**
      
  + **Spring과 SpringBoot의 차이점**  
    + **`Spring`**
      + 자바 엔터프라이즈 애플리케이션 개발을 쉽게하기 위한 오픈소스 애플리케이션 프레임워크
      + **개발자가 직접** 설정 파일을 작성하여 `스프링 컨테이너 구성`
      + **개발자가 직접** 필요한 `빈 객체 등록`
      + **개발자가 직접** 빈 객체 간의 `의존성 설정`
    + **`SpringBoot`**
      + **`Spring`에서 제공하는 여러 기능들을 자동으로 설정**하여 개발자가 보다 쉽게 사용할 수 있도록 하는 도구
      + SpringBoot 설정 파일 : `application.properties`, `application.yaml` or `application.yml`
--- 
### 2. Spring IoC/DI
  + **`Bean`**
    + 스프링 컨테이너에서 싱글톤 패턴으로 관리되는 객체
    + 애플리케이션을 모듈화하고 유지 보수를 용이하게 하는 역할
    + 다양한 부분에서 재사용되거나 주입되어 사용
    + 같은 자료형의 Bean이 2개 이상일 때는 이름을 명시하여 DI
      ```java
        // 같은 자료형의 Bean (game & game2)
        public Game game() { ... }
        public Game game2() { ... }

        // game2에 매핑하여 DI
        @Autowired @Qualifier("game2") Game game;
      ```
   
  + **Bean 생성 방법**
    1. `@Configuration` + `@Bean`
       + @Configuration 파일의 메소드를 통해 @Bean 객체 반환
          ```java
            @Configuration
            public class BeanConfig {
              @Bean
              public String bean1() {
                return new String("Bean 1");
              }
            }
          ```
    2. ~~XML 파일 Element 작성~~ (오래된 방식이라, 하락세)
    3. `@ComponentScan`
       + 스프링에서 제공하는 4가지 어노테이션을 통해 클래스를 명시하여 Bean으로 등록
         + `@Component` : 아래 3가지 어노테이션의 상위 객체
         + `@Controller` : 요청과 응답을 처리하는 클래스에 사용 (ex. Spring MVC)
         + `@Service` : 비즈니스 로직을 구현하는 클래스에 사용 (ex. Service Class)
         + `@Repository` : Persistence 역할을 하는 클래스에 사용 (ex. DAO class)
       ```java
         @Component
         public class Bean3 {
           public String run() {
             return "Bean 3";
           }
        }
       ```
  + 빌드 자동화 관리 도구
    + 필요한 라이브러리 종류와 버전들, 종속성 정보를 명시하여 필요한 라이브러리들을 설정 파일을 통해 자동으로 다운로드 해주고 이를 간편히 관리해주는 도구
    + `Maven`
      + Java용 프로젝트 관리 도구
      + 빌드 중인 프로젝트, 빌드 순서, 다양한 외부 라이브러리 종속성 관계를 `pom.xml 파일`에 명시
      + 외부저장소에서 필요한 라이브러리와 플러그인들을 다운로드 후, 로컬시스템의 캐시에 모두 저장
        ```
          <dependencies>
             <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
                <version>7.0.0-M4</version>
             </dependency>
          </dependencies>
        ```
    + `Gradle`
      + Groovy 언어를 사용한 Domain-specific-language를 사용하는 프로젝트 빌드 관리 툴
      + Gradle은 프로젝트의 어느 부분이 업데이트되었는지 알기 때문에, 빌드에 점진적으로 추가 가능
      + 업데이트가 이미 반영된 빌드의 부분은 즉 더이상 재실행 X &rarr; 빌드 시간 단축 가능
        ```
          dependencies {
            implementation("org.springframework:spring-core:7.0.0-M4")
          }
        ```
    + `Maven vs Gradle`
      + Maven은 고정적이고 선형적인 단계의 모델을 기반 vs Gradle은 작업 의존성 그래프를 기반
      + Maven의 경우 멀티 프로젝트에서 특정 설정을 다른 모듈에서 사용하려면 상속 vs Gradle 은 설정 주입 방식
      + Maven & Gradle 모두 다중 모듈 빌드를 병렬로 실행 가능
      + Gradle은 이미 업데이트된 테스크에 대해서는 작업이 실행되지 않으므로 빌드 시간이 훨씬 단축
      + Gradle은 concurrent에 안전한 캐시 허용
      + 속도나 캐시 사용 안전성에 대하여 당연히 **Gradle로 사용하는게 이득이겠다는 생각**
--- 
### 3. Spring MVC
  + **`Spring Web MVC`**
    ![Spring Web MVC](https://velog.velcdn.com/images/minpic/post/9dc88eb4-3be5-4d42-a09e-b0f6f173b6bd/image.png)
    + Model – View – Controller 의 약자
    + Presentation 과 Business 를 분리시키기 위해 사용
    + `요청`에 따라 `Controller` 매핑 & `응답`으로 `View` 처리
    + **`Annotation` 종류**
      + Controller 명시 : `@Controller`, `@RestController`
      + Mapping 명시 : `@RequestMapping`, `@GetMapping`, `@PostMapping`
      + Parameter 명시 : `@RequestParam`, `@ModelAttribute`, `@PathVariable`
      + JSON Response 명시 : `@ResponseBody`

  + **`응답 처리`**
    + 종류 : `HTML` 형식, `JSON` 형식
    + **`HTML Template Response`**
      + 반환 타입 : **`String`**, `void`, `Map`, `Model`, `ModelAndView`, `DTO` 
      + 방법 : `@Controller` + `return String type`
      + 사용 예시
        ```java
          @GetMapping("html/string")
          public String htmlString() {
            return"html/string"; // templates/html/string.html 파일에 매핑
          }
        ```
    + **`JSON Template Response`**
      + 반환 타입 : **`Map`, `DTO`, `List`**
        + 타입 별로 브라우저 반응이 달라짐 (ex. Header의 Content-type을 다르게 전송)
        + `ResponseEntity`를 통해 응답 패킷을 조정 가능 (ex. header의 Content-type 변경, body 내용 담기)
      + 방법
        1. `@Controller` + `@ResponseBody` + `return (Map/List/DTO) type`
        2. `@RestController` + `return (Map/List/DTO) type`
      + 사용 예시
        1. `@RestController` + Return `Map` Type 
           ```java
             @RestController
             public class Json2Controller{
                @GetMapping("json2/map")
                public Map<String, Object> jsonMap(Map<String, Object> map) {
                  Map<String, Object> map2 = newHashMap<String, Object>();
                  map2.put("key1", "value");
                  map2.put("key2", 2324);
                  map2.put("key3", true);
                  return map2;
                }
             }
           ```
        2. `@Controller` + `@ResponseBody` + Return `DTO` Type 
           ```java
              @GetMapping("json1/object")
              @ResponseBody
              public Member jsonObject() {
                Member member= newMember();
                member.setName("kim");
                return member;
              }
           ```
        3. `@RestController` + Return `List` Type 
           ```java
             @RestController
             public class Json2Controller{
                @GetMapping("json2/list")
                public List<String> jsonList() {
                  List<String> list = newArrayList<>();
                  list.add("1");
                  list.add("2");
                  list.add("3");
                  return list;
                }
             }
           ```
  
  + **`요청 처리`**
    + **`Mapping`**
      + 종류 : **`GET`, `POST`**, `PUT`, `DELETE`
      + `GET` METHOD 사용 예시
        ```java
          // @RestController 사용
          @GetMapping("req/get")
          // == @RequestMapping(value= "req/get", method= RequestMethod.GET)
          public String get() {
            return "GET";
          }
        ```
      + `POST` METHOD 사용 예시
        ```java
          // @RestController 사용
          @PostMapping("req/post")
          // == @RequestMapping(value= "req/post", method= RequestMethod.POST)
          public String post() {
            return "POST";
          }
        ```
    + **`Parameter`**
      + 종류 : ~~`HttpServletRequest`~~, **`@RequestParam`, `@ModelAttribute`**, `@RequestBody`, `@PathVariable`
        + ~~`HttpServletRequest` : 모든 파라미터는 문자열로 전달 (필요 시, 타입 변환하여 사용)~~
        + **`@RequestParam`**
          + 단순 데이터(ex. 페이지, 검색어, 카테고리 등)에 사용됨
          + 소괄호를 통해 `defaultValue` 및 `required` 등과 같은 옵션 지정 가능
          + `@RequestParam("key1")`와 같이 파라미터 명을 지정하지 않으면 변수명을 파라미터 명에 맞춰 사용 必
          + 사용 예시
            1. 일반 파라미터 타입
               ```java
                 // localhost:8080/req/param1?key1=v1&key2=v2 로 요청
                 @GetMapping("req/param1")
                 public String param1(
                    @RequestParam String key1,
                    @RequestParam("key2") String key2
                 ) {
                    return key1+ ", "+ key2;
                 }
               ```
            2. `Map` 파라미터 타입 (전달된 모든 파라미터를 동적으로 사용)
               ```java
                 // localhost:8080/req/param2?search=java&version=1.8 로 요청
                 @GetMapping("req/param2")
                 public String param2(@RequestParam Map<String, Object> map) {
                   return map.toString(); // map : {"search":"java", "version":"1.8"}
                 }
               ```
        + **`@ModelAttribute`**
          + 복잡한 데이터(ex. `Model`, `DTO`, `VO` 등의 객체)에 사용됨
          + `MyBatis`, `JPA` 등과 같은 ORM 프레임워크에서 활용됨
          + `Model`에 작성 되어 있는 변수 명과 자료 형태가 요청 파라미터와 동일하면 자동으로 대입
          + 사용 예시
            ```java
              @GetMapping("req/model")
              public String model(@ModelAttribute Member member) {
                return member.toString();
              }
            ```
        + `@PathVariable`
          + 요청 주소의 경로명 활용 (ex. `/user/abc/1`)
          + 사용 예시
            ```java
              @GetMapping("req/path/{path1}/{path2}")
              public String path(
                @PathVariable("path1") String path1,
                @PathVariable("path2") String path2
              ) {
                return path1+ ", "+ path2;
              }
            ```
        + `@RequestBody`
          + 메소드 방식을 `POST` 요청에서만 사용 가능
          + AJAX 요청 & JSON 형태의 데이터를 통신 시 주로 사용 (ex. React)
          + JSON 형태의 파라미터 사용 (ex. Query String Parameter, Form Data, Payload)
            
      + AJAX 요청 파라미터 전달 방법
        1. [GET] Query String
           ```javascript
             const url = `http://localhost:8080/register-ajax2?name=${name}&age=${age}&email=${email}`;
            const res = await fetch(url);
           ```
        2. [POST] Form Data
           ```javascript
              const formData = new FormData();
              formData.append("name", name.value);
              formData.append("age", age.value);
              formData.append("email", email.value);
              
              const res = await fetch(url, {
                method: 'post',
                body: formData
              });
           ```
        3. [POST] Json Data
           ```javascript
             const obj = {
                "name": name.value, "age": age.value, "email": email.value
              };
              const res = await fetch(url, {
                method: 'post',
                headers: { "content-type": 'application/json' },
                body: JSON.stringify(obj)
              });
           ```
      + **`CORS`**
        + Cross Origin Resource Sharing의 약자
        + 브라우저에서 다른 출처(Protocol, Host, Port 동일 여부)의 리소스를 공유하는 방법
        + **기본적을호 보안을 위해 다른 도메인이나 포트의 요청을 차단**
        + `@CrossOrigin` 또는 WebMvcConfigurer의 설정을 통해 허용 가능
        + 필요성 : 프론트와 백엔드의 서버 분리, 다른 출처의 데이터 사용, 마이크로 서비스 아키텍처
          
      + **`SWAGGER`**
        + **`REST API` 문서를 자동으로 생성하는 도구**
        + 어노테이션
          + `@Tag`
            + API를 그룹 별로 정리해주는 역할
            + ex. `@Tag(name="User API", description="사용자 관련 API")`
          + `@Operation`
            + 메소드에 대해 설명하는 역할
            + ex. `@Operation(summary="사용자 조회", descript="ID를 기준으로 사용자 정보를 조회")`
              + `summary` : 간단한 설명 (Swagger UI 리스트에 보임)
              + `description` : 상세 설명 (API 상세 페이지에 보임)
          + `@Parameter`
            + 요청 파라미터에 대한 설명
            + `@RequestParam`, `@PathVariable` 등의 요청 값에 대한 설명을 표시할 때 사용
            + ex. `@Parameter(description="사용자 이름", example="홍길동")`
          + `Schema`
            + `DTO 클래스` 및 `DTO 필드`를 설명하는데 사용
            + 사용 예시
              +  DTO 클래스 : `@Schema(description="사용자 응답 객체")`
              +  DTO 클래스 필드 : `@Schema(description="사용자 이름", example="홍길동")`
--- 
### 4. Spring Database
  + `ORM`
    + Object Relational Mapping의 약자
    + 어플리케이션과 DB 연결 시, SQL언어가 아닌 어플리케이션 개발 언어로 DB 접근할 수 있게 해주는 도구
    + 개발 언어의 일관성과 가독성을 높임
    + 객체와 관계형 데이터베이스의 테이블을 자동으로 매핑해주는 기술  
      | 언어 | 대표 ORM | 특징 요약 |
      |:----:|:--------:|:---------:|
      | Java | JPA (Hibernate 등)| 표준 API, 구현체 다양, 가장 많이 사용됨 |
      | Python | SQLAlchemy, Django ORM| 유연하고 강력, 프레임워크 연동 쉬움 |
      | JavaScript | TypeORM, Sequelize, Prisma| Type 기반, 프론트와 연동 용이 |
      | PHP | Eloquent, Doctrine| 사용 간편, Laravel/Symfony에서 활용 |
      | Ruby | ActiveRecord| Rails 내장, 직관적 DSL 문법 |
      | C# | Entity Framework, Dapper| LINQ 지원, 경량 ORM인 Dapper도 인기 |
      | Go | GORM, Ent| 코드 생성 기반, 성능 우수 |
      
  + `Spring JDBC`
    ![Spring JDBC](https://velog.velcdn.com/images%2Fkoseungbin%2Fpost%2Fffb11dc7-6ca6-4e4d-9071-425cc4f4a4fb%2Fimage.png)
    + [ [사용 방법](https://github.com/NamSangwon/LG_CNS_AM_INSPIRE_CAMP_2/commit/269efa9265788ccd0bc3de555891f7b58bc05537) ]
      1. `Model` 클래스 구성 (송수신 데이터 정의)
      2. `@Repository` 역할을 수행하는 `DAO Class` 생성하여 DB 제어 메소드(직접적인 SQL 명령어) 작성
      3. `Controller`에서 `DAO Class`를 의존성 주입한 후, `DAO Class`의 DB 제어 메소드 호출
      + [ 사용 예시 ]
        + `Map` 형태로 조회
          + JdbcTemplate의 `queryForList()` 메소드 사용
        + `Model` 형태로 조회
          + `RowMapper<T>` 인터페이스의 `mapRow()` 메소드를 오버라이드한 후, 인자로 전달
          + queryForList()와 같은 기능을 하도록 구현 &rarr; 비효율적인 방식
        + 데이터 입력 및 삭제
          + JdbcTemplate의 `update` 메소드를 통해 쿼리 실행
            
  + `MyBatis`
    ![MyBatis](https://linked2ev.github.io/assets/img/devlog/201909/mybatis-s1.png)
    + 자바에서 관계형 데이터베이스를 좀 더 쉽게 개발할 수 있도록 하는 프레임워크
    + SQL 문장들을 별도의 파일로 구성 (SQL 분리) &rarr; 코드의 간결성 및 유지보수성 향상
    + [ [사용 방법](https://github.com/NamSangwon/LG_CNS_AM_INSPIRE_CAMP_2/commit/81aadae5a143fb504c7603d91df89e924b6ca10b) ]
      1. `application.properties` : XML 파일 위치 지정
      2. `Configuration` : Mapper 인터페이스 위치 지정
      3. `Mapper 인터페이스` : 추상 메소드 생성
      4. `XML` : Mapper 인터페이스의 메소드 명과 동일한 id 지정 후 SQL 문 작성
      5. `Controller` : 의존성 주입된 `Mapper 인터페이스`의 추상 메소드 호출
         
  + **`JPA (Java Persistence API)`**
    ![JPA](https://blog.kakaocdn.net/dn/bWYkaF/btqEmu3EfwQ/UcUSJCMK9TEN4fUbmApXW1/img.png)
    + `ORM 프레임워크` 사용 : 객체는 객체대로, 관계형 데이터베이스는 관계형 데이터베이스대로 설계
    + [ 특징 ]
      + DAO와 Database Table의 강한 의존성 문제 해결
      + Entity를 작성하면 자동으로 Table 생성
      + SQL 문장을 이용하지 않고 메소드를 호출하면 자동으로 SQL 문장 실행
      + **유지 보수에 용이 & 특정 DB에 종속적이지 않음(Dialect)**
    + [ Table 생성 옵션 ]
      + `create` : 기존 테이블을 삭제 후 생성 (DROP + CREATE)
      + `create-drop` : create 기능 + DROP (DROP + CREATE + DROP)
      + `update` : 변경된 내용만 Alter 적용
      + `validate` : 엔티티와 테이블이 정상 매핑되었는지만 확인
      + `none` : 자동 생성 기능 사용하지 않음
      + [ 주의 사항 ]
        + **운영 서버는 절대 `create`, `create-drop`, `update` 사용 금지**
        + 스테이징 및 운영 서버 : `validate` or `none`
        + 개발 초기 : `create` or `update`
        + 테스트용 서버 : `update` or `validate`
    + `Mapping Annotation`
      + 사용 예시
        ```java
          @Entity(name = "table_exam_1")
          public class TableExam1 {
            @Id
            Integer id;
            @Column(length = 100, nullable = false)
            String title;
            @Column(name = "description", length = 1000, nullable = true)
            String content;
            Long price;
            String brand;
          }
        ```
      + `@Entity` (필수) : JPA가 관리할 객체로 등록
        + 클래스 멤버 변수가 테이블의 칼럼으로 적용 시, Camel Case &rarr; Snake Case로 변경
        + 데이터가 `null` 값이면, 기본 자료형으로 처리 불가 &rarr; `Wrapper 클래스` 사용
      + `@Id` (필수) : 기본키로 사용될 값을 지정
        + `@GeneratedValue`
          + `IDENTITY` : 데이터베이스에 위임 (auto_increment)
          + `SEQUENCE` : 오라클 등의 시퀀스 객체 사용 
          + `TABLE` : KEY룰 관리하는 테이블 사용
          + `UUID` : 겹칠 확률이 매우 낮은 식별자 생성
          + `AUTO (default)` : 위 전략 중 자동으로 선택
      + `@Table` : Entity와 매핑할 테이블 이름 명시 (생략 시, Entity 이름을 테이블 이름으로 판단)
      + `@Column` : 테이블 칼럼과 관련된 속성 지정 (ex. nullable, length, ...)
      + `@Enumerated` : 열거형 데이터 (ex. ORDINAL, STRING, ...)
      + `@Temporal` : 날짜 데이터 (ex. DATE, TIME, TIMESTAMP)
      + `@Lob` : 대용량 데이터 (ex. CLOB(char), BLOB(byte))
      + `@Transient` : DB에 저장하거나 조회가 필요 없는 데이터 (ex. 비밀번호 확인, 가입 시 약관에 동의)
    + **[ Repository 인터페이스 ]**
      + 데이터를 입력 / 조회 / 수정 / 삭제 하기 위해서 사용
      + **객체 (Entity) 와 관계형 데이터베이스를 매핑하여 데이터베이스의 SQL을 자동으로 생성**
      + SQL 자동 생성 메소드 제공
        + 입력/수정 : `save()`
        + 삭제 : `delete()`
        + 조회 : `findById()` or `findAll()`
      + 사용 예시
        + Entity
          ```java
            @Entity
            @Data
            public class ServiceCenter {
              @Id
              @GeneratedValue
              Integer id;
              String customer;
            }
          ```
        + Repository
          ```java
            @Repository
            public interface ServiceCenterRepository
              extends JpaRepository<ServiceCenter, Integer> {
            }
          ```
        + Controller
          ```java
            @Autowired
            ServiceCenterRepository serviceCenterRepository;
            @GetMapping("/sc/add")
            @ResponseBody
            public ServiceCenter scAdd(@ModelAttribute ServiceCenter sc) {
              ServiceCenter result = serviceCenterRepository.save(sc);
              return result;
            }
            @GetMapping("/sc/list")
            @ResponseBody
            public List<ServiceCenter> scList() {
              List<ServiceCenter> result = serviceCenterRepository.findAll();
              return result;
            }
          ```
    + [ 페이징 및 정렬 ]
      + `Pageable 인터페이스` 사용
      + 페이징 : 한 페이지에 보여줄 데이터의 개수를 설정하여 데이터를 페이지 단위로 나눔
      + 정렬 : 특정 필드를 기준으로 데이터를 정렬하여 조회
      + 사용 예시
        ```java
          void select3() {
            // 정렬 속성 지정
            Order order1 = Order.asc("survived");
            Order order2 = Order.desc("age");
            Sort sort = Sort.by(order1, order2);
            // 페이징 방식 지정 (파라미터 : 페이지, 원소 개수, 정렬 방식)
            Pageable pageable = PageRequest.of(0, 10, sort);
            // 검색 결과
            Page<Titanic> list = titanicRepository.findAll(pageable);
            // 출력
            list.get().forEach(item -> System.out.println(item));
          }
        ```
    + **[ 연관관계 ]**
      + 객체 간의 관계를 표현하고 데이터베이스 테이블 간의 관계를 연결하는 역할 (단방향/양방향)
      + `@ManyToOne` : 다대일 관계 표현
      + `@JoinColumn` : 외래키 지정
      + `@OneToMany` : 일대다 관계 표현
      + [ [사용 예시](https://github.com/NamSangwon/LG_CNS_AM_INSPIRE_CAMP_2/commit/49389ff4639853c4208dad7ac09d546dc15a63f6) ]
        + `Player`
          ```java
          public class Player {
            @Id
            int playerId;
            String playerName;
            @ManyToOne
            // 외래키 명시
            @JoinColumn(name="team_id")
            Team team;
          }
          ```
        + `Team`
          + 단방향
            ```java
              public class Team {
                @Id
                int teamId;
                String teamName;
              }
            ```
          + 양방향
            ```java
              public class Team {
                @Id
                int teamId;
                String teamName;
                @OneToMany(mappedBy = "team") // Player Entity의 Team 변수명
                List<Player> players = new ArrayList<>();
              }
            ```
      + **[ 발생하는 문제 ]**
        + `LazyInitialization` : JPA에서 관리하는 DB 세션이 종료 된 후, 관계가 설정된 엔티티를 참조하려고 할 때 발생
          + DB 세션 = Persistant Context(영속성 컨텍스트) (트랜잭션과 생명 주기를 같이 함)
          + [ 해결 방법 ]
            1. `@OneToMany(fetch=FetchType.EAGER)` (즉시 로딩)
               + Entity를 조회할 때 연관된 Entity도 함께 조회 (연관된 객체가 모두 영속성 컨텍스트에 생성)
               + 성능 문제 발생 가능성 有 (권장 X)
            2. `@Transactional(readOnly = true)`
               + Repository에서 세션을 시작해 밖에서도 세션이 종료되지 않고 유지하도록 하는 방법
               + 즉,  `@Transactional`이 명시된 메소드가 종료되면 세션을 종료
               + `readOnly`를 통해 Dirty Checking을 하지 않도록 하여 성능을 향상시키고, 데이터의 의도하지 않은 변경을 방지
            3. `Fetch Join (JPQL or QueryDSL)`
               + 지연 로딩이 걸려있는 연관 관계에 대해서 한 번에 같이 즉시 로딩
               + Repository에서 JPQL 작성 시, 같이 가져올 데이터를 명시적으로 지정
            4. `@EntityGraph` (== Fetch Join)
               + RDB에도 JPA 엔티티와 같이 연관관계가 설정되어 있지 않은 경우
               + 즉시 로딩으로 연관관계의 객체를 조회해오고 Left Outer Join으로 읽어오도록 함
               + 연관관계의 상위 엔티티를 중복 조회하기 때문에 JPQL 사용 시, distinct 설정을 사용하거나 Set 컬렉션을 사용하는 것을 권장
                 
        + `순환 참조` : 두 개 이상의 빈(Bean)이 서로를 참조할 때 발생하는 문제
          + [ 해결 방법 ]
            1. `@ToString(exclude="순환 참조 변수")`
               + 순환되는 부분을 제외하고 출력하도록 함
               + 근본적인 해결책 X
            2. `@JsonIgnore` & `@JsonIgnoreProperties({"posts"})`
               + JSON 데이터에 해당 프로퍼티는를 포함시키지 않도록 함
            3. `@JsonManagedReference` + `@JsonBackReference`
               + 순환 참조 명시
               + 부모 클래스(Posts entity)의 Comment 필드에 @JsonManagedReference를 추가
               + 자식 클래스(Comment entity)의 Posts 필드에 @JsonBackReference를 추가
            4. `DTO 클래스` 사용
               + 근본적인 원인 : 양방향 매핑이기도 하지만, Entity 자체를 response로 리턴
               + 따라서, entity 자체를 return 하지 말고 DTO 객체를 만들어 필요한 데이터만 옮겨 담아 리턴
            
        + [`N + 1`](https://velog.io/@jinyoungchoi95/JPA-%EB%AA%A8%EB%93%A0-N1-%EB%B0%9C%EC%83%9D-%EC%BC%80%EC%9D%B4%EC%8A%A4%EA%B3%BC-%ED%95%B4%EA%B2%B0%EC%B1%85) : 조회 시 1개의 쿼리 + 나오지 않아도 되는 조회의 쿼리가 N개가 더 발생하는 문제
          + 예시 : `User` 한 명이 쓴 `Article`들을 조회할 때 `User`-`Article`을 join한 형태의 쿼리문을 원했지만, N개의 `Article`을 또 조회하는 쿼리가 날아가는 경우
          + [ 즉시/지연 로딩에서의 문제 ]
            + `Fetch Join`로 해결 : 지연 로딩이 걸려있는 연관 관계에 대해서 한 번에 같이 즉시 로딩
            + `@EntityGraph`로 해결 : `Fetch Join`의 하드 코딩을 최소화하는 방법
            + But, `Pagination` or `2개 이상의 collection join`에서 해결 불가
          + [ `Pagination`에서의 문제 ]
            + `fetch join` 시 limit, offset을 통한 쿼리가 아닌 `인메모리`에 모두 가져와 application단에서 처리하여 `OOM(Out Of Memory)` 발생
            + `BatchSize`를 통해 필요 시 배치 쿼리로 원하는 만큼 쿼리를 날림 > 쿼리는 날아가지만 N번 만큼의 무수한 쿼리는 발생되지 않음
          + [ `2개 이상의 Collection Join`에서의 문제 ]
            + `List` 자료구조의 `2개 이상의 Collection join(~ToMany관계)`에서 `fetch join` 할 경우 `MultipleBagFetchException` 예외 발생
            + `Set` 자료구조를 사용한다면 해결가능 (`Pagination`은 여전히 발생)
            + `BatchSize`를 사용한다면 해결가능 (`Pagination` 해결)
        
  + [ `DTO` 변환 필요성 ]
    + 프로세스 간에 데이터를 전달하는 객체(DTO, Data Transfer Object) 사용
    + API의 응답을 세분화하여 필요한 상황에 맞는 필요한 필드만 DTO로 매핑하여 리턴
    + [ 장점 ]
      + 데이터 크기 조절 가능 (응답에 필요 없는 오버헤드를 줄일 수 있음)
      + DTO를 통해 명확하고 간결한 코드 작성 가능
      + DTO대신 Entity를 사용하면 Entity 구조가 모두 노출되어 보안에 취약 한 점을 해결 가능
      + 응답할 데이터를 API마다 달리함 &rarr; 유지 보수성 향상
              
    + [ JUnit ]
      + 단위 테스트 프레임워크
      + 기능을 테스트하고 검증할 때, 사용
      + `@Test`가 있는 메소드 단위로 테스트 실행
      + 사용 예시
        ```java
          // JUnit Test
          @SpringBootTest
          class DemoApplicationTests {
            @Autowired AssemblyMemberRepository assemblyMemberRepository;
          
            // [데이터 1 건 조회] 테스트
            @Test
            void contextLoads() {
              // Null Pointer Exception 오류를 피하기 위해 Optional<T> 클래스를 사용
              Optional<AssemblyMember> opt = assemblyMemberRepository.findById(1);
              
              AssemblyMember member = new AssemblyMember();
              if (opt.isPresent())
              {
                member = opt.get();
              }
          
              // (In VSC) DEBUG CONSOLE > [Launch Java Tests - ...]에 출력
              System.out.println(member);
          
              // 조회한 결과가 맞는 지, 확인하는 코드 (assertXXX() 메소드)
              // 조회한 데이터의 id 가 1인지 확인하여, 조회 성공 여부 확인
              int id = member.getId();
              assertEquals(1, id);
            }
        ```
        
    + [ QueryMethod ]
      + 메소드 이름을 통해 SQL을 생성하는 방식
      + 인터페이스 작성 시, JPA가 SQL을 구현
      + 규칙 : `리턴타입` `접두어` `도입부` `By` `속성표현식` `조건식` `And/Or` `속성표현식` `조건식` `정렬조건` `(매개변수)`
