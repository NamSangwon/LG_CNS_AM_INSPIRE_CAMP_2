# 3주차 학습 내용

### 1. 자바의 특징
  + 이식성이 높은 언어이다.
    + **운영체제에 독립적이다.**
    + 자바 컴파일러를 통해 바이트 코드(.class 파일)로 변환 후 자바 가상 머신(JVM)에서 실행되기 때문이다.
  + 객체 지향 언어 : `캡슐화`, `상속`, **`다형성`**
  + Garbage Collection을 통해 메모리를 자동으로 관리

### 2. 변수
  + 선언 : 변수 생성 (ex. `int value;`)
  + 초기화 : 변수 생성 + 값 할당 (ex. `String value = "문자열";`)
  + 변수는 읽기 전에 반드시 초기화되어야 함

### 3. 자료형
  + 기본 자료형
    <table>
      <tr>
        <td>값의 종류</td>
        <td>기본 타입</td>
        <td colspan='2'>메모리 사용 크기</td>
        <!-- <td>메모리 사용 크기</td> -->
        <td>저장되는 값의 범위</td>
      </tr>
      <tr>
        <td rowspan='5'>정수</td>
        <td>byte</td>
        <td>1 byte</td>
        <td>8 bit</td>
        <td>-2<sup>7</sup> ~ 2<sup>7</sup>-1 (-128 ~ 127)</td>
      </tr>
      <tr>
        <!-- <td rowspan='5'>정수</td> -->
        <td>char</td>
        <td>2 byte</td>
        <td>16 bit</td>
        <td>0 ~ 2<sup>16</sup>-1 (0 ~ 65,535)</td>
      </tr>
      <tr>
        <!-- <td rowspan='5'>정수</td> -->
        <td>short</td>
        <td>2 byte</td>
        <td>16 bit</td>
        <td>-2<sup>15</sup> ~ 2<sup>15</sup>-1 (-32,768 ~ 32,767)</td>
      </tr>
      <tr>
        <!-- <td rowspan='5'>정수</td> -->
        <td>int</td>
        <td>4 byte</td>
        <td>32 bit</td>
        <td>-2<sup>31</sup> ~ 2<sup>31</sup>-1 (-2,147,483,648 ~ 2,147,483,647)</td>
      </tr>
      <tr>
        <!-- <td rowspan='5'>정수</td> -->
        <td>long</td>
        <td>8 byte</td>
        <td>64 bit</td>
        <td>-2<sup>63</sup> ~ 2<sup>63</sup>-1</td>
      </tr>
      <tr>
        <td rowspan='2'>실수</td>
        <td>float</td>
        <td>4 byte</td>
        <td>32 bit</td>
        <td>유효자릿수(정밀도) : 소수점 7자리</td>
      </tr>
      <tr>
        <!-- <td rowspan='2'>실수</td> -->
        <td>double</td>
        <td>8 byte</td>
        <td>64 bit</td>
        <td>유효자릿수(정밀도) : 소수점 15자리</td>
      </tr>
      <tr>
        <td>논리</td>
        <td>boolean</td>
        <td>1 byte</td>
        <td>8 bit</td>
        <td>true or false</td>
      </tr>
    </table>
  + 참조 자료형 : 8개의 기본 자료형을 제외한 나머지 타입
    + 객체의 주소를 저장
    + ex. 자바 API가 제공하는 클래스 or 사용자가 생성한 클래스
  + 형변환 (Casting) : 값의 타입을 다른 타입으로 변환
    + 기본 자료형 : `boolean`을 제외한 7개 자료형은 서로 형변환 가능
    + 참조 자료형 : 상속 관계인 경우 형변환 가능
    + **자동 형변환 : 프로그램 실행 도중 작은 타입은 큰 타입으로 자동 타입 변환 가능**
      + 서로 다른 타입의 피연산자는 같은 타입으로 변환
      + `두 피연산자 중 크기가 큰 타입으로 자동 변환`
    + 강제 형변환 : 큰 타입을 작은 타입으로 간제 타입 변환
    + **사용 이유**
      + 컴파일러 오류 방지
      + 의도적인 표시
      + 형 변환 듀칙에 위배되지만 변환이 필요한 상황
      + 참조 자료형의 다형성 적용
  + 오버/언더 플로우(Overflow / Underflow) : 자료형의 범위를 벗어나면, 가장 (작은/큰) 값으로 적용되는 현상
    + ex) 1byte : `127 (0111 1111)` + `1 (0000 0001)` -> `-128 (1000 0000)`
    + 맨 앞의 bit는 부호 비트이므로 다음과 같이 나타남

### 4. 연산자
  + 연산자 종류
    <table style="border-collapse: collapse; width: 100%; height: 242px;" border="1" data-ke-style="style8">
      <tbody>
          <tr style="height: 22px;">
              <td style="width: 14.186%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">연산자 종류</span></p>
              </td>
              <td style="width: 26.6279%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">연산자</span></p>
              </td>
              <td style="width: 13.1396%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">피연산자 수</span></p>
              </td>
              <td style="width: 7.67442%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">산출값</span></p>
              </td>
              <td style="width: 38.3721%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">설명</span></p>
              </td>
          </tr>
          <tr style="height: 22px;">
              <td style="width: 14.186%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">산술 연산</span></p>
              </td>
              <td style="width: 26.6279%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">+, -, *, /, %</span></p>
              </td>
              <td style="width: 13.1396%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 7.67442%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">숫자</span></p>
              </td>
              <td style="width: 38.3721%; height: 22px; text-align: left;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 사칙연산 및 나머지계산 한다.</span></p>
              </td>
          </tr>
          <tr style="height: 22px;">
              <td style="width: 14.186%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">부호</span></p>
              </td>
              <td style="width: 26.6279%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">+, -</span></p>
              </td>
              <td style="width: 13.1396%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">단항</span></p>
              </td>
              <td style="width: 7.67442%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">숫자</span></p>
              </td>
              <td style="width: 38.3721%; height: 22px; text-align: left;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 음수 / 양수 부호</span></p>
              </td>
          </tr>
          <tr style="height: 22px;">
              <td style="width: 14.186%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">문자열</span></p>
              </td>
              <td style="width: 26.6279%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">+</span></p>
              </td>
              <td style="width: 13.1396%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 7.67442%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">문자</span></p>
              </td>
              <td style="width: 38.3721%; height: 22px; text-align: left;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 두 문자를 연결시킨다.</span></p>
              </td>
          </tr>
          <tr style="height: 22px;">
              <td style="width: 14.186%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">대입 연산</span></p>
              </td>
              <td style="width: 26.6279%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">=, +=, -=, *=, /=, %=</span></p>
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&amp;=, ^=, |=, &lt;&lt;=,
                          &gt;&gt;=, &gt;&gt;&gt;=</span></p>
              </td>
              <td style="width: 13.1396%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 7.67442%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">다양</span></p>
              </td>
              <td style="width: 38.3721%; height: 22px; text-align: left;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 우변의 값을 좌변의 변수에 대입</span></p>
              </td>
          </tr>
          <tr style="height: 22px;">
              <td style="width: 14.186%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">증감 연산</span></p>
              </td>
              <td style="width: 26.6279%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">++, --</span></p>
              </td>
              <td style="width: 13.1396%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">단항</span></p>
              </td>
              <td style="width: 7.67442%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">숫자</span></p>
              </td>
              <td style="width: 38.3721%; height: 22px; text-align: left;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 1만큼 증가 / 감소</span></p>
              </td>
          </tr>
          <tr style="height: 22px;">
              <td style="width: 14.186%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">비교 연산</span></p>
              </td>
              <td style="width: 26.6279%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">==, !=, &lt;, &gt;, &lt;=,
                          &gt;=,</span></p>
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">instanceof</span></p>
              </td>
              <td style="width: 13.1396%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 7.67442%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">boolean</span></p>
              </td>
              <td style="width: 38.3721%; height: 22px; text-align: left;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 값의 비교</span></p>
              </td>
          </tr>
          <tr style="height: 22px;">
              <td style="width: 14.186%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">논리 연산</span></p>
              </td>
              <td style="width: 26.6279%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">!, &amp;, |, &amp;&amp;, ||</span>
                  </p>
              </td>
              <td style="width: 13.1396%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">단항</span></p>
              </td>
              <td style="width: 7.67442%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">boolean</span></p>
              </td>
              <td style="width: 38.3721%; height: 22px; text-align: left;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 논리적 NOT, AND, OR 연산</span>
                  </p>
              </td>
          </tr>
          <tr style="height: 22px;">
              <td style="width: 14.186%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">조건 연산</span></p>
              </td>
              <td style="width: 26.6279%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">(조건식) ? A : B</span></p>
              </td>
              <td style="width: 13.1396%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">삼항</span></p>
              </td>
              <td style="width: 7.67442%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">다양</span></p>
              </td>
              <td style="width: 38.3721%; height: 22px; text-align: left;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 조건식에 따라 참이면 A, 거짓이면 B
                          선택</span></p>
              </td>
          </tr>
          <tr style="height: 22px;">
              <td style="width: 14.186%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">비트</span></p>
              </td>
              <td style="width: 26.6279%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">~, &amp;, |, ^</span></p>
              </td>
              <td style="width: 13.1396%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">단항</span></p>
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 7.67442%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">숫자</span></p>
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">boolean</span></p>
              </td>
              <td style="width: 38.3721%; height: 22px; text-align: left;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 비트 NOT, AND, OR, XOR
                          연산</span></p>
              </td>
          </tr>
          <tr style="height: 22px;">
              <td style="width: 14.186%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">비트 쉬프트</span></p>
              </td>
              <td style="width: 26.6279%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&gt;&gt;, &lt;&lt;,
                          &gt;&gt;&gt;</span></p>
              </td>
              <td style="width: 13.1396%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 7.67442%; text-align: center; height: 22px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">숫자</span></p>
              </td>
              <td style="width: 38.3721%; height: 22px; text-align: left;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 비트를 좌 / 우측으로 밀어서 이동</span>
                  </p>
              </td>
          </tr>
      </tbody>
  </table>
  
  + 연산자 우선 순위
    <table style="border-collapse: collapse; width: 86.9871%; height: 300px;" border="1" data-ke-style="style8">
      <tbody>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">우선순위</span></p>
              </td>
              <td style="width: 54.5198%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">연산자</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">피연산자</span></p>
              </td>
              <td style="width: 9.60628%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">연산 방향</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">0</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; ( ) 괄호 속 연산자</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">다양</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">-</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">1</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 증감 ( ++, -- ), 부호 ( +, - ),
                          비트 ( ~ ), 논리 ( ! )</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">단항</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';"><b>←</b></span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">2</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 산술 ( *. / % )</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">→</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">3</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 산술 ( +, - )</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="color: #333333; font-family: 'Nanum Gothic';">→</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">4</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 쉬프트 ( &gt;&gt;, &lt;&lt;,
                          &gt;&gt;&gt; )</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="color: #333333; font-family: 'Nanum Gothic';">→</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">5</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 비교 ( &lt;, &gt;, &lt;=,
                          &gt;=, instanceof)</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="color: #333333; font-family: 'Nanum Gothic';">→</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">6</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 비교 ( ==, != )</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="color: #333333; font-family: 'Nanum Gothic';">→</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">7</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 논리&nbsp; &amp;</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항(단항)</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="color: #333333; font-family: 'Nanum Gothic';">→</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">8</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 논리&nbsp; ^</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항(단항)</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="color: #333333; font-family: 'Nanum Gothic';">→</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">9</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 논리&nbsp; |</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항(단항)</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="color: #333333; font-family: 'Nanum Gothic';">→</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">10</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 논리 &amp;&amp;</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="color: #333333; font-family: 'Nanum Gothic';">→</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">11</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 논리 ||</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="color: #333333; font-family: 'Nanum Gothic';">→</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">12</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 조건 ( ?&nbsp; : )</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">삼항</span></p>
              </td>
              <td style="width: 9.60628%; height: 20px; text-align: center;">
                  <p data-ke-size="size18"><span style="color: #333333; font-family: 'Nanum Gothic';">→</span></p>
              </td>
          </tr>
          <tr style="height: 20px;">
              <td style="width: 10.5426%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">13</span></p>
              </td>
              <td style="width: 54.5198%; text-align: left; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">&nbsp; 대입 <br>&nbsp; ( =, +=, -=,
                          *=, /=, %=, &amp;=, ^=, |=, &lt;&lt;=, &gt;&gt;=, &gt;&gt;&gt;= )</span></p>
              </td>
              <td style="width: 13.9613%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';">이항</span></p>
              </td>
              <td style="width: 9.60628%; text-align: center; height: 20px;">
                  <p data-ke-size="size18"><span style="font-family: 'Nanum Gothic';"><b>←</b></span></p>
              </td>
          </tr>
      </tbody>
  </table>
    
  + 이항 연산 중 피연산자의 종류가 다르면 **자동 형변환의 규칙**에 따라 `두 피연산자 중 크기가 큰 타입으로 자동 변환`

### 5. 조건문
  + `if 문` : 조건식의 결과에 따라 동작
  + `switch 문` : 변수의 값에 따라 동작
  + `break` : `switch 문` 종료 

### 6. 반복문
  + `for 문` : 반복 횟수를 알고 있을 때, 주로 사용
  + `while 문` : 반복 횟수를 모르고 조건에 따라 반복할 때, 주로 사용
  + `break` : 반복문 종료
  + `continue` : continue문 이후의 문장들을 수행하지 않고 다음 반복으로 넘어 감.

### 7. String 클래스
  + **String 참조**
    + 대입되는 값이 같은 경우 : 힙 영역에 하나의 String 객체만을 생성하여 같은 객체를 참조
      + `new 연산자`를 사용하여 같은 값을 같더라도 서로 다른 두 객체로써 생성 가능
    + 대입되는 값이 다른 경우 : 힙 영역에 두 개의 String 객체를 생성하여 각각 객체를 참조
  + **String 메소드**
    <table border="1" style="border-collapse: collapse; width: 100%;">
      <thead style="background-color: #d9edf7; font-weight: bold;">
        <tr>
          <th>메소드</th>
          <th>기능</th>
          <th>메소드</th>
          <th>기능</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>equals</td>
          <td>문자열 비교</td>
          <td>length</td>
          <td>문자열 길이</td>
        </tr>
        <tr>
          <td>charAt</td>
          <td>문자 추출</td>
          <td>indexOf</td>
          <td>문자열 위치</td>
        </tr>
        <tr>
          <td>replace</td>
          <td>문자열 변경</td>
          <td>replaceAll</td>
          <td>문자열 변경 (정규 표현식)</td>
        </tr>
        <tr>
          <td>trim</td>
          <td>공백 제거</td>
          <td>split</td>
          <td>구분자 기준 분리</td>
        </tr>
        <tr>
          <td>substring</td>
          <td>문자열 자르기</td>
          <td>String.format</td>
          <td>형식에 맞춰 문자열 생성</td>
        </tr>
      </tbody>
    </table>

  + 문자열 처리 클래스
    + `String`, `StringBuffer`, `StringBuilder`
    + 속도 : `String` < `StringBuffer` < `StringBuilder`
      + `StringBuffer`은 Thread Safe (Synchronized)
      + `StringBuffer`은 Not Thread Safe
      + 따라서,  `StringBuffer`는 `StringBuilder`보다 느림

### 8. 배열

### 9. 메소드

### 10. 클래스

### 11. 생성자

### 12. 상속
