# 4주차 학습 내용 2 - 데이터베이스

### 1. 데이터베이스
  + DBMS (Database Management System) : 여러 사람들이 공유하고 사용할 목적으로 통합 관리되는 정보의 집합
    
  + SQL (Structured Query Language)
    + DDL (Data Definition Language) - 정의어 : 테이블 생성, 변경 제거
    + DML (Data Manipulation Language) - 조작어 : 테이블에 데이터 삽입, 검색, 수정, 삭제
    + DCL (Data Control Language) - 제어어 : 데이터 사용 권한 부여 및 취소
   
  + Key 종류
    + **Primary Key, PK (기본키)** : 고유한 값 중 선택된 컬럼
    + **Foreign Key, FK (외래키)** : 다른 테이블의 기본키를 참조하는 컬럼
    + Candidate Key (후보키) : 기본키로 선택될 수 있는 컬럼
    + Alternate Key (대체키) : 기본키로 선택되지 않은 후보키
    + Super Key (슈퍼키) : 여러개의 컬럼을 묶어서 고유한 값으로 사용되는 컬럼의 집합
   
  + 무결성 제약 조건 : 데이터의 무결성을 보장하고 일관된 상태로 유지하기 위한 규칙
    + 개체 무결성 제약조건 (PK) : 기본키를 구성하는 값은 NULL 불가
    + 참조 무결성 제약조건 (FK) : 기본키로 존재하지 않는 값은 외래키로 사용 불가 (null은 가능)
---
### 2. DDL
  + 테이블 생성
    ```sql
      CREATE TABLE 테이블명 (
        속성명 데이터타입 [NOT NULL] [DEFAULT 기본값]
        [PRIMARY KEY (속성 리스트)]
        [UNIQUE (속성 리스트)]
        [FOREIGN KEY (속성 리스트) REFERENCES 테이블명 (속성 리스트)]
        [ON DELETE 옵션] [ON UPDATE 옵션]
      );
    ```
      
  + 테이블 제거
    ```sql
      DROP TABLE 테이블명;
    ```
   
  + 테이블 변경
    + 속성 추가
      ```sql
        ALTER TABLE 테이블명
        ADD 속성명 데이터타입 [NOT NULL] [DEFAULT 기본값];
      ```
    + 외래키(제약조건) 추가
      ```sql
        ALTER TABLE 테이블명
        ADD [CONSTRAINT 제약조건명] FOREIGN KEY(속성명) REFERENCES 참조테이블명(참조속성명);
      ```
    + 속성 변경
      ```sql
        ALTER TABLE 테이블명 MODIFY (속성명 데이터타입);
      ```
    + 속성 삭제
      ```sql
        ALTER TABLE 테이블명 DROP 속성명 [CASCADE | RESTRICT];
      ```
    + 외래키(제약조건) 삭제
      ```sql
        ALTER TABLE 테이블명 DROP FOREIGN KEY 제약조건명;
      ```
    + 제약조건 확인
      ```sql
        SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
        WHERE TABLE_NAME = 테이블명;
      ```

---
### 3. DML
  + 데이터 삽입
    ```sql
      # INTO 의 속성과 VALUES 속성은 순서대로 1:1 대응되어야 됨
      INSERT INTO 테이블명
      [(속성 리스트)]
      VALUES (속성의 값 리스트);
    ```
  + 데이터 조회
    + 기본
      ```sql
        # DISTINCT 사용 시 검색 결과 중 중복 튜플은 하나만 출력
        SELECT [DISTINCT] 속성 리스트
        FROM 테이블 리스트
      ```
    + 속성명 변경 출력 `AS`
      ```sql
        # 변경하는 속성명에 공백이 포함되어 있으면 따옴표로 묶어줌
        # AS 키워드 생략 가능
        SELECT 속성명 [AS] 변경속성
        FROM 테이블
      ```
    + 산술식을 이용한 검색
      ```sql
        # 실제 속성의 값은 변하지 않고 결과 테이블에서만 계산된 값이 출력
        SELECT 속성명 + 숫자, 속성명 * 숫자, …
        FROM 테이블
      ```
    + **조건에 해당하는 데이터 검색**
      ```sql
        # 비교 / 논리 연산자 등을 이용하여 검색 조건 작성
        SELECT [DISTINCT] 속성 리스트
        FROM 테이블 리스트
        [WHERE 조건];
      ```
      + 부분적으로 일치하는 데이터 검색
        ```sql
          # 문자열을 이용하는 조건에만 적용 가능
          WHERE 속성명 LIKE '검색 데이터';
        ```
      + `NULL` 데이터 검색
        ```sql
          # IS 키워드를 사용하지 않고 비교연산자 이용 시 항상 거짓으로 출력
          WHERE 속성명 IS NULL;
        ```
    + 데이터 검색 후 정렬
      ```sql
        # 속성명을 여러 개 사용하는 경우 왼쪽 속성을 우선으로 정렬
        SELECT [DISTINCT] 속성 리스트
        FROM 테이블
        [WHERE 조건]
        [ORDER BY 속성 리스트 [ASC | DESC]];
      ```
  + 데이터 수정
    ```sql
      # WHERE 절을 생략하면 테이블에 존재하는 모든 튜플을 대상으로 수정
      UPDATE 테이블명
      SET 속성 이름1 = 값1, 속성 이름2 = 값2, …, 속성 이름n = 값n;
      [WHERE 조건];
    ```
    
  + 데이터 삭제
    ```sql
      # WHERE 절을 생략하면 테이블에 존재하는 모든 튜플을 대상으로 삭제
      DELETE
      FROM 테이블명
      [WHERE 조건];
    ```
---
### 4. Function Group
  + [단일행 함수](https://m.blog.naver.com/doredome/222855964477)
    ![단일행 함수](https://mblogthumb-phinf.pstatic.net/MjAyMjA4MjNfNzAg/MDAxNjYxMjI4ODU1MDg2.6iKs6sxiOSQ1j2vN7PstgOr_opnpFIklF9-Kic_3PcQg.VdvL2b-rgWeoofH-XHcMqSWm7-ikqgRvzygbEOBqd4Mg.PNG.doredome/image.png?type=w800)
  + 조건문 (`CASE`)
    ```sql
      SELECT 고객아이디, 고객이름, 등급,
        CASE
          WHEN 등급='silver' THEN '일반고객'
          WHEN 등급='gold' THEN '우수고객'
          WHEN 등급='vip' THEN '최우수고객'
        END
      FROM 고객;
    ```
  + 집계 함수 : 통계적 계산 결과 검색
    + 속성 값이 NULL인 경우 제외
    + SELECT 절 또는 HAVING 절 에서만 사용 가능 (WHERE 절 사용 불가)
      ![SQL 집계 함수](https://velog.velcdn.com/images/h220101/post/77cdfc0b-3889-4bbc-9786-093da0e250cd/image.png)
  + 그룹별 검색
    + 특정 속정의 값이 같은 튜플을 모아 그룹을 만들어 그룹별 검색 가능
    + SELECT 절에는 그룹으로 묶은 속성과 집계 함수만 사용 가능
    + HAVING 키워드를 이용하여 그룹에 대한 조건 작성 가능
      ```sql
        SELECT [DISTINCT] 속성 리스트
        FROM 테이블 리스트
        [WHERE 조건]
        [GROUP BY 속성 리스트 [HAVING 조건]]
        [ORDER BY 속성 리스트 [ASC | DESC]]
      ```
---
### 5. Join
  + 여러 개의 테이블을 연결하여 하나의 테이블처럼 사용하는 것
  + 조인 속성 : 조인 검색을 위해 테이블 간의 연결되는 속성 *(일반적으로 외래키가 조인 속성으로 사용)*
  
  + [조인의 종류](https://sparkdia.tistory.com/17)
    + `INNER JOIN`
      + 매칭되는 데이터만 표현
      + **Equi 조인** : 가장 일반적으로 사용하는 Equality Condition (=) 에 의한 조인
      + Non-Equi 조인 : 컬럼 값이 일치하지 않을 때 (<, >, BETWEEN 등 사용)
    + `SELF JOIN` : Equi 조인과 같으나 하나의 테이블만 사용해서 조인
    + **`OUTER JOIN`**
      + 연결되는 컬럼 값이 없더라도 데이터를 검색해주는 조인
      + 기준이 되는 테이블을 Outer, 반대쪽을 Inner 라고 표현
      + 종류 : LEFT, RIGHT (+ FULL)
---
### 6. Subquery
  + SELECT 문 안에 또 다른 SELECT 문을 포함하는 질의
  + 소괄호로 묶어서 작성
  + order by와 limit을 제외한 나머지 절에 사용
  + 다중 행 부속 질의문은 별도의 연산자를 사용해야 실행 가능
    <table>
      <thead>
        <tr>
          <td>연산자</td>
          <td>설명</td>
          <td>사용 예</td>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>[NOT] IN</td>
          <td>부속 질의문의 결과 값 중 <span>일치 여부</span>에 따라 조회</td>
          <td>직업이 회사원인 고객들이 주문한 일자</td>
        </tr>
        <tr>
          <td>[NOT] EXISTS</td>
          <td>부속 질의문의 결과 값 중 <span>존재 여부</span>에 따라 조회</td>
          <td>2013년도에 한번도 주문을 한적이 없는 고객의 직업</td>
        </tr>
      </tbody>
    </table>
  + 사용 예시
    ```sql
      # 적립금이 가장 많은 고객의 고객이름과 적립금 검색
      SELECT 고객이름, 적립금
      FROM 고객
      WHERE 적립금 = (
        SELECT MAX(적립금)
        FROM 고객
      );
    ```
  + 데이터베이스 성능에 영향을 줄 수 있음 (성능 저하)
---
