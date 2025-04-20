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
---
### 4. Function Group

---
### 5. Join

---
### 6. Subquery

---
