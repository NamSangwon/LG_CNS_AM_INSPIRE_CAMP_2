# 10주차 학습 내용

### 1. MSA
  + **`Monolothic Architecture`**
    + **모든 업무 로직이 하나의 애플리케이션 형태로 패키징되어 서비스**
    + **장점**
      + 서비스 초기에 빠른 개발 가능
      + 소규모 프로젝트에서는 합리적
      + 개발, 빌드, 배포, 테스트 용이
    + **단점**
      + 비대해지는 공통 코드
      + 의존관계 복잡성 증가
      + 전체 코드 이해 ↓ &rarr; 코드 수정 ↓, copy & paste ↑
      + Branch Merge 시, Conflict 확률 증가
      + 코드 수정 시, 프로젝트 전체를 빌드/배포
      + 어려운 배포 일정 관리
      + 개발자가 많아질 수록 개발 효율성 감소
      + 기능 별로 알맞는 기술, 언어, 프레임워크 선택 어려움
  + **`MSA`**
    + **하나의 큰 어플리케이션을 여러 개의 작은 어플리케이션으로 쪼개어 변경과 조합이 가능하도록 만든 아키텍처**
    + **요구사항**
      + 각각의 서비스는 독립적으로 배포 가능해야 함
      + 각각의 서비스는 다른 서비스에 대한 의존성이 최소화 되어야 함
      + 각각의 서비스는 개별 프로세스로 구동
      + 각각의 서비스끼리 통신 시, REST와 같은 가벼운 방식 사용
    + **장점**
      + 마이크로 서비스 단위로 작업을 할당하여, 개발자가 자신이 맡은 코드를 온전히 이해 가능
      + 수정 사항이 있는 마이크로 서비스만 빠르게 빌드, 배포 가능
      + 해당 기능에 맞는 기술, 언어, 프레임워크 등을 선택하여 사용 가능
      + 코드 오류 시, 해당 코드에만 오류가 발생하여 빠르게 수정하여 정상화 가능
    + **단점**
      + 마이크로 서비스들이 분산되어 모니터링 어려움 &rarr; 관리 어려움
      + 마이크로 서비스들은 서로를 호출하여 전체 서비스를 이룸 &rarr; 개발 용이성 ↓
      + 마이크로 서비스들 끼리 계속 서로 통신 &rarr; 통신 오류 가능성 ↑
      + 전체 서비스를 테스트하기 위해 여러 개의 마이크로 서비스를 구동 必 &rarr; 테스트 불편성 ↑
  + **12 Factors**
    1. `코드 베이스 (Codebase)`
       + 어플리케이션 당 1개의 코드베이스
       + 각 어플리케이션은 1개의 코드베이스를 통해 운영/개발에 배포
    2. `의존성 명시 (Dependencies)`
       + 어플리케이션은 모든 종속성을 명시적으로 선언 (ex. build.gradle, pom.xml, requirements.txt, etc)
       + 종속성은 외부 환경과 분리 (ex. `Docker`)
    3. `설정 분리 (Config)`
       + 어플리케이션 내 설정 값을 소스코드에서 분리하여 독립적으로 관리 (ex. DB 설정, URL, 인증 정보 등)
       + MSA 환경에서의 Config 파일 관리 방법
         + Spring Cloud Config
         + Kubernetes ConfigMap
    4. `백엔드 서비스 (Backing Services)`
       + 로컬 및 서드파티 서비스를 구별하지 않고 모두 연결된 리소스로 취급
       + 리소스들은 자유롭게 연결되거나 분리하여 코드 수정 없이 전환 가능해야 함
       + ex. DB를 MySQL에서 Amazon RDS로 코드 수정 없이 전환 가능해야 함
    5. `빌드, 출시, 배포 (Build, Release, Run)`
       + 코드 베이스는 (build > release > run) 세 단계를 거쳐 배포
       + 각 단계는 엄격하게 분리 必
         + `Build`
           + build.gradle
         + `Release`
           + Jenkinsfile
           + Artifact -> Docker Image -> Container Registry
         + `Run (Deploy)`
           + Kubernetes Deployment
           + Container Registry -> Kubernetes
    6. `프로세스 (Process)`
       + 어플리케이션은 하나 이상의 무상태성 프로세스로 실행 가능해야 함
       + 무상태성 : 어플리케이션 내부의 디스크 및 메모리에 저장하는 쿠키나 세션등을 갖지 않는 상태
       + 유지될 필요가 있는 데이터는 DB or Cache 같은 백엔드 서비스에 저장 必
    7. `포트 바인딩 (Port Binding)`
       + 배포된 어플리케이션을 다른 어플리케이션에서 접근할 수 있도록 공개 必
       + 포트 바인딩을 통해 서비스 공개
         + 컨테이너 기반으로 한 서버 내에서 다양한 서비스가 독립적으로 수행
           + 자기 완비적 : 내장 톰캣을 통한 자가 실행
         + 포트 바인딩을 통해 개별 포트로 들어오는 요청을 처리
    8. `동시성 (Concurrency)`
       + 각 어플리케이션을 수평으로 확장 가능해야 함 &rarr; Scale Out
       + Scale Out : 서비스를 여러 개 구동하여 시스템을 확장
       + Scale Up : 기존 서버의 사양을 업그레이드해 시스템을 확장
    9. `폐기 가능 (Disposability)`
       + MSA 환경에서 각 어플리케이션들은 빈번하게 시작되고 종료됨
         + 간단하게 바로 시작하거나 종료될 수 있는 탄력성 必
         + `Graceful shutdown`: Shutdown 시 DB lock 등으로 타 프로세스에 영향을 주어서는 안됨
           + 새로운 요청을 받지 않음
           + 진행 중인 요청 마무리 (트랜잭션 Commit or Rollback)
           + 자원 정리 후 앱 종료 (DB Connection 등)
    10. `개발/프로덕션 환경 일치 (Dev/Prod Parity)`
        + Dev, Staging, Production 환경을 최대한 동일하게 유지
          + 배포 시간의 차이를 최소화: 개발자가 작성한 코드는 몇 시간 또는 몇 십분 내에 배포
          + 담당자의 차이를 최소화: 개발자가 Production 모니터링에 깊게 관여
          + 툴의 차이를 최소화: Dev, Staging, Production 환경을 최대한 비슷하게 유지
    11. `로그 (Logs)`
        + 로그를 이벤트 스트림으로 취급
        + 로그를 로컬 파일에 저장 X
          + 어플리케이션은 언제든지 생성/삭제 가능 &rarr; 로컬에 저장된 로그 파일은 초기화됨
        + 중앙 집중식 로그 (`EFK` 사용)
    12. `관리자 프로세스 (Admin Processes)`
        + 일회성 admin 프로세스 : 다른 프로세스들처럼 코드베이스와 설정을 기반으로 함
          + 일회성 admin 프로세스 예 : DB 마이그레이션, 일회성 스크립트 등
        + admin 프로세스 코드는 동기화 문제를 피하기 위해 어플리케이션 코드와 함께 배포
  + **`필요 기술`**
    + `Service Discovery`
      ![image](https://github.com/user-attachments/assets/9e132836-175b-4720-a3e7-5285437f439c)
      + **각각의 마이크로 서비스가 동적으로 확장/축소될 때마다 해당 서비스의 정보(IP, Port)를 수동으로 업데이트 해야하는 점이 불편한 점을 해결하기 위한 서비스**
      + `Service Registry` : 서비스의 위치(IP, Port 등)를 저장 및 관리하는 서비스의 주소록
      + [작동 순서]
        1. 서비스 등록
           + 서비스 시작 시, 인스턴스 정보를 Service Registry에 등록
           + 서비스 종료 시, Service Registry에서 해제
           + 주기적으로 HeartBeat 신호를 통해 서비스 생존 여부를 Service Registry에 알림
        2. 서비스 조회
           + 다른 서비스가 특정 서비스와 통신할 때, Service Registry에 서비스 이름 질의
           + Service Registry는 요청된 서비스의 활성화된 인스턴스들의 IP 및 포트 정보 반환
        3. 서비스 사용
           + 서비스 소비자 Service Registry로부터 받은 정보를 통해 통신 시작
    + `Client LoadBalancer`
      ![image](https://github.com/user-attachments/assets/a0b01abc-aa6b-4cc0-95af-cc271ea2e8fb)
      + 클라이언트에 탑재되어 있는 Load Balancer
      + **서비스를 호출하는 애플리케이션이 직접 로드 밸런싱 로직을 가지고 요청을 분산하는 방식**
      + Service Discovery Server에서 N 개의 Service Discovery Client 정보를 받아 온 후, 그 정보를 바탕으로 Round Robin 과 같은 알고리즘을 통해 로드 밸런싱
      + Hardware 가 필요 없이 Software 로만 가능
      + IP와 PORT가 아닌 서비스 이름으로 호출
    + `API Gateway`
      ![image](https://github.com/user-attachments/assets/99d205df-7fc1-4809-94be-5ba713fae291)
      + 모든 서버로의 요청을 단일지점을 거쳐서 처리
      + **사용자가 설정한 엔드포인트로 외부 요청을 라우팅 하는 역할**
      + 클라이언트는 직접 마이크로 서비스에 요청을 보내지 않고 API Gateway에 요청
      + 기능 : 인증 및 권한 부여, 라우팅, 속도 제한, 부하 분산, 로깅 추적, 공통 로직 처리 등
    + `Container`
      + 소프트웨어 및 관련 라이브러리, 시스템 도구, 코드 및 런타임 요소들을 패키징하여 격리된 실행 환경을 제공하는 기술
    + `Container Orchestration`
      + 많은 컨테이너화된 애플리케이션의 배포, 관리, 확장 및 네트워킹을 자동화하는 프로세스
    + `CI/CD`
      + 지속적 통합 및 지속적 배포 (Continuous Integration & Continuous Deployment)
      + 자동 빌드 및 테스트 수행 후 배포
---
### 2. 클라우드 네이티브
---
### 3. Docker
---
### 4. Spring Cloud MSA
---
