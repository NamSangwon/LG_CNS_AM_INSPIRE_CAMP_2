# 4주차 학습 내용

### 1. 오버라이딩 (Overriding)
  + **부모 클래스로부터 상속받은 메소드를 자식 클래스에 맞게 변경**
  + `@Override 어노테이션` : 컴파일러에게 부모 클래스의 메소드 선언부와 동일한지 검사 지시
  + `super 키워드` : 자식 클래스에서 부모 클래스의 메소드 및 필드에 접근해야 하는 경우 사용
---
### 2. 오버로딩 (Overloading)
  + **클래스 내에 같은 이름의 메소드를 여러 개 선언하는 것**
  + 조건
    + `매개변수의 타입`, `매개변수의 개수`, `매개변수의 순서`가 달라야 한다.
    + `메소드`의 이름이 같아야 한다.
---
### 3. 다형성
  + **한 타입의 참조 변수를 통해 여러 타입의 객체를 참조할 수 있도록 하는 것**
  + **`자동 타입 변환 (Up Casting)`**
    + 프로그램 실행 도중에 일어난다.
    + 상속 계층의 상위 관계인 객체인 경우, 자동 타입 변환 가능 (ex. 자식 클래스 &rarr; 부모 클래스)
    + 변환 후에는 변환된 클래스 타입 멤버만 접근 가능
  
  + **`강제 타입 변환 (Down Casting)`**
    + 상속 계층의 상위 객체를 하위 객체로 변환하는 것 (ex. 부모 클래스 &rarr; 자식 클래스)
    + **조건 : 자식 타입을 부모 타입으로 자동 변환 후, 다시 자식 타입으로 변환할 때**
    + 사용 예 : 자식 클래스가 부모 클래스로 자동 변환 후, 자식 클래스에 선언된 필드와 메소드를 사용하려는 경우

  + **`객체 타입 확인 (instanceof)`**
    + 강제 타입 변환을 실행하기 전, 객체가 특정 클래스에 속하는지 아닌지를 확인하기 위해 사용
    + 사용 예 : `Parent 객체`를 `Child 객체`로 변환하려 할 시, `Parent 객체`가 참조하는 객체의 타입이 `Child 객체`인지 확인
---
### 4. 추상 클래스 (Abstract Class)
  + **실체 클래스의 공통적인 부분(필드 및 메소드)을 추출해 어느정도 규격을 잡아놓은 추상적인 클래스**
    + 설계 : 추상 클래스에서 실체 클래스에 반드시 존재해야 할 필드 와 메소드를 선언
    + 구현 : 실체 클래스는 추상 클래스를 상속한 후, 오버라이드를 통해 동작할 코드 작성 
  + *추상 메소드, 생성자, 필드, 일반 메소드 포함 가능*
  + 클래스이므로 `단일 상속`만 가능 (`extends` 키워드 사용)
  ``` java
    // 추상 클래스 (abstract 키워드 사용)
    public abstract class Animal { 
      // 추상 메소드 (abstract 키워드 사용)
      // - 추상 클래스 내에서 구현 불가 (중괄호 사용 불가) (= 미완성 메소드)
      // - 실체 클래스에서 반드시 추상 메소드를 완성해야 함
      public abstract void sound(); 
    }
  ```
---
### 5. 인터페이스 (Interface)
  + **클래스들이 구현해야 하는 동작을 지정하는데 사용되는 추상 자료형**
  + 추상 클래스보다 추상화 정도가 더 높다. *(상수 및 추상 메소드만 포함 가능)*
  + 클래스와 달리 `다중 상속` 가능 (`implements` 키워드 사용)
  ``` java
    // 인터페이스 (interface 키워드 사용)
    public interface Calc {
      public abstract int add(int num1, int num2); // 추상 메소드 명시 (abstract 키워드)
      public int substract(int num1, int num2); // 추상 메소드 (컴파일 시, 자동 변환)
    }
  ```
  + **람다식**
    + 메소드를 한 줄로 표현하는 간단한 표현 방법
    + 하나의 동작만 정의된 인터페이스에만 적용 가능
      ```java
        interface Calculator {
          int add(int a, int b);
        }
        
        Calculator c = (a, b) -> a + b; // 람다식 = (매개변수) -> { 실행문 }
        System.out.println(c.add(3, 5));
      ```
---
### 6. 접근 제어자
  + 패키지
    + 클래스를 유일하게 만들어주는 식별자 역할 (물리적인 형태는 파일 시스템의 폴더)
    + **클래스명이 동일하더라도 패키지가 다르면 다른 클래스로 인식**
    + 이름 규칙 : `java`로 시작하는 패키지는 `표준 API`에서만 사용 + 모두 소문자

  + 접근 제어자 (Access Modifier)
    + 클래스 및 클래스의 구성 멤버에 대한 접근을 제한하는 역할
      <table>
        <thead>
          <tr>
            <td>지시자</td>
            <td>클래스 내부</td>
            <td>동일 패키지</td>
            <td>상속받은 클래스</td>
            <td>이외의 영역</td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>private</td>
            <td>O</td>
            <td>X</td>
            <td>X</td>
            <td>X</td>
          </tr>
          <tr>
            <td>default</td>
            <td>O</td>
            <td>O</td>
            <td>X</td>
            <td>X</td>
          </tr>
          <tr>
            <td>protected</td>
            <td>O</td>
            <td>O</td>
            <td>O</td>
            <td>X</td>
          </tr>
          <tr>
            <td>public</td>
            <td>O</td>
            <td>O</td>
            <td>O</td>
            <td>O</td>
          </tr>
        </tbody>
      </table>
---
### 7. 예외 (Exception)
  + 종류
    + 컴파일 오류 (Compile Error) : 컴파일 불가 (ex. `int a = 1.2;`)
    + 런타임 오류 (Runtime Error) : 실행 중 오류 (ex. `int a = 4 / 0;`)
    + 논리 오류 (Logical Error) : 버그 (흐름상 잘못된 코딩)
   
  + **예외 처리 방법**
    + **예외 발생 시, 프로그램 종료를 막고 정상 실행을 유지할 수 있도록 처리**
    + **`try-catch-finally` 블록을 통해 예외 처리 코드 작성**
      + Exception (일반 예외) : 반드시 작성해야 컴파일 가능
      + Runtime Exception (실행 예외) : 개발자 경험 의해 작성 (컴파일시 체크 X)
      + *`catch 문`에 예외의 종류를 입력*
      +  예외 별로 `catch 문`을 여러 개 사용하여 *다중 예외*를 처리
    + **`throws`를 통해 예외 떠넘기기**
      + 일반적으로 도구 및 라이브러리를 개발하는 개발자가 예외 처리를 넘기기 위해 사용
      ```java
        public void mehtod1() throws ClassNotFoundException { ... }

        public void method() {
          try { method1(); }
          catch(ClassNotFoundException e) {
            System.out.println(e.getMessage()); // getMessage() : 예외 정보 출력 메소드
            // e.printStackTrace(); // 예외의 발생 경로를 추적한 내용을 모두 콘솔에 출력하는 메소드
          } 
          Finally { ... }
        }
      ```
  + 의도적인 예외 발생 : `throw ner Exception("Error Message");`
  + 예외 클래스의 계층 구조
    + `Exception 클래스` : 사용자의 실수와 같은 외적인 요인에 의해 발생하는 예외 **(체크 예외)**
    + `Runtime Exception 클래스` : 프로그래머의 실수로 발생하는 예외 **(언체크 예외)**
    + 체크/언체크 예외 : `try-catch 문`을 통해 예외 처리 필요/불필요
   
  + 사용자 정의 예외 클래스
    + 자바 표준 API에서 제공하지 않는 예외를 사용하려는 경우 (ex. 회원 가입 실패 예외 등)
    + ex) `public Class XXXException extends [Exception | RuntimeException] { ... }`
---
### 8. 자바 컬렉션 프레임워크
  + 프로그램 구현에 필요한 자료구조를 구현해 놓은 라이브러리 (java.util 패키지에 구현되어 있음)
  + 종류
    + `List` 인터페이스 : **`ArrayList`**, `Vector`, `LinkedList`, **`Stack`**, **`Queue`**
    + `Set` 인터페이스 : **`HashSet`**, `TreeSet`
    + `Map` 인터페이스 : **`HashMap`**, `Hashtable`, `TreeMap`, `Properties`
      
  + **`Generic`**
    + 컴파일하면서 타입을 검사하는 문법
    + 기본 자료형은 사용이 불가능하고 반드시 참조 자료형으로 사용
    + 따라서, 기본 자료형은 `Wrapper 클래스`를 기반으로 사용
    + ex) `byte` &rarr; `Byte`, `char` &rarr; `Character`, `int` &rarr; `Integer`, etc.
    
  + **`Iterator`**
    + Collection의 개체를 순회하는 `인터페이스`
    + `hasNext()` 메소드를 통해 다음 요소가 있는지 확인 (boolean 반환)
    + `next()` 메소드를 통해 다음에 있는 요소를 반환
---
### 9. I/O Stream
  + 종류
    + `Byte Stream` : 바이너리 데이터 (인코딩 X)
    + `Character Stream` : 문자 데이터 (UTF-8, EUC-KR 등과 같은 형식으로 인코딩된 데이터)
    + 데이터를 복사할 경우, 안전성을 위해 Byte Stream 사용
    <table>
      <thead>
        <tr>
          <td rowspan=2>구분</td>
          <td colspan=2>바이트 기반 스트림</td>
          <!-- <td colspan=2>바이트 기반 스트림</td> -->
          <td colspan=2>문자 기반 스트림</td>
          <!-- <td colspan=2>문자 기반 스트림</td> -->
        </tr>
        <tr>
          <!-- <td rowspan=2>구분</td> -->
          <td>입력 스트림</td>
          <td>출력 스트림</td>
          <td>입력 스트림</td>
          <td>출력 스트림</td>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>최상위 클래스</td>
          <td>InputStream</td>
          <td>OutputStream</td>
          <td>Reader</td>
          <td>Writer</td>
        </tr>
        <tr>
          <td>하위 클래스</td>
          <td>XXXInputStream<br>(File/Buffered/Data)</td>
          <td>XXXOutputStream<br>(File/Print/Buffered/Data)</td>
          <td>XXXReader<br>(File/Buffered/InputStream)</td>
          <td>XXXWriter<br>(File/Buffered/Print/OutputStream)</td>
        </tr>
      </tbody>
    </table>

  + 보조 스트림
    + 다른 스트림과 연결되어 여러가지 편리한 기능을 제공하는 스트림
    + Input/OutputStream, Reader/Writer 와 연결하여 입출력 수행
    ```java
      // 보조 스트림 예시
      InputStream in = new FileInputStream("character stream data.txt");
      InputStreamReader isr = new InputStreamReader(in); // 보조 스트림 1
      BufferedReader reader = new BufferedReader(isr); // 보조 스트림 2
    
      OutputStream out = new FileOutputStream("character stream data copy.txt");
      OutputStreamWriter osw = new OutputStreamWriter(out); // 보조 스트림 1
      BufferedWriter writer = new BufferedWriter(osw); // 보조 스트림 2
    ```
  + File 클래스
    + [다양한 메소드](https://www.geeksforgeeks.org/file-class-in-java/)들을 사용하여 파일 및 폴더 제어
---
### 10. 쓰레드
  + `프로세스`
    +  **실행 중인 하나의 프로그램**
    +  **운영체제로부터 실행에 필요한 메모리를 할당받아 실행**
      
  + `쓰레드`
    + **프로세스 내에서 독립적으로 실행되는 작은 실행 단위**
    + 한 가지 작업을 실행하기 위해 순차적으로 실행되는 코드 (1개의 쓰레드는 1개의 코드 실행)
    + 멀티 쓰레드 : 1개의 프로세스 내에서 2가지 이상의 작업을 처리

  + 메인 쓰레드
    + `main()` 메소드가 실행될 때, 메인 쓰레드가 시작
    + 필요에 따라 멀티 쓰레드를 만들어 병렬로 코드 실행 가능
    + 실행 중인 쓰레드가 하나라도 있으면, 프로세스는 종료 X
  
  + 작업 쓰레드
    + 생성 : `Thread 클래스` 또는 `Runnable 인터페이스`를 상속받는 클래스 생성
      + `Thread 클래스`를 상속한 `MyThread 클래스`를 생성하여 작업 쓰레드 생성
      + `Thread 클래스` 생성 시, `Runnable 인터페이스`를 상속한 `MyThread 클래스`를 인자로 넘겨 작업 쓰레드 생성
    + 실행 : 작업 쓰레드의 `start()` 메소드 호출 &rarr; Thread 객체 내의 `run()` 메소드가 실행됨
      ```java
        // Thread 클래스 상속
        public class ThreadExam1 extends Thread {
          String name;
          ThreadExam1(String name) { this.name = name; }
          public void run() { ... }
        }
        // Runnable 인터페이스 상속
        public class ThreadExam2 implements Runnable {
          String name;
          ThreadExam2(String name) { this.name = name; }
          public void run() { ... }
        }

        public static void main(String[] args)
        {
          // Thread 클래스 상속
          ThreadExam1 t1 = new ThreadExam1("Thread Class");
      
          // Runnable 인터페이스 상속
          ThreadExam2 r = new ThreadExam2("Runnable Interface");
          Thread t2 = new Thread(r);

          t1.start();
          t2.start();  
        }
      ```

  + 쓰레드 동기화
    + 하나의 자원(객체)을 공유하는 경우, **동시성 오류** 주의
    + 오류를 해결하기 위해, 하나의 쓰레드가 사용 중인 객체는 다른 쓰레드가 접근할 수 없도록 잠금
    + `synchronized` 키워드 사용
      1. `synchronized` 메소드
        + 메소드 전체에 대해 동기화 적용
        + 코드가 간결하고 메소드 내의 전체 코드 보호
        + 불필요한 블로킹이 발생될 수 있음
      2. `synchronized` 블록
         + 블록 구간 내에 동기화
         + 코드가 조금 더 복잡해질 수 있지만 정밀하게 제어할 수 있음
         + 필요한 부분만 잠그므로 성능에 효율적

---
### 11. JDBC

---

### 1. Database

---
