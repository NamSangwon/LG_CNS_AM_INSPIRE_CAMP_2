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
  + `Spring Web MVC`
    ![Spring Web MVC](https://velog.velcdn.com/images/minpic/post/9dc88eb4-3be5-4d42-a09e-b0f6f173b6bd/image.png)
    + Model – View – Controller 의 약자
    + Presentation 과 Business 를 분리시키기 위해 사용
    + `요청`에 따라 `Controller` 매핑 & `응답`으로 `View` 처리
    + **`Annotation` 종류**
      + Controller 명시 : `@Controller`, `@RestController`
      + Mapping 명시 : `@RequestMapping`, `@GetMapping`, `@PostMapping`
      + Parameter 명시 : `@RequestParam`, `@ModelAttribute`, `@PathVariable`
      + JSON Response 명시 : `@ResponseBody`

  + `응답 처리`
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
  
  + `요청 처리`
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
      + **`@ModelAttribute`**
      + `@RequestBody`
      + `@PathVariable`
--- 
### 4. Spring Database
  + `Spring JDBC`
  + `MyBatis`
  + `JPA`
--- 
