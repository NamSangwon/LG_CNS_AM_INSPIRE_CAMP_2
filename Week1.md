# 1주차 학습 내용

### 1. HTML
+ 웹페이지의 구조를 **태그(\<tagname\>)** 로 표시하는 문서
+ (<, >, &, ", ') 등과 같은 문자는 **특수한 표현 (Entity Name)** 으로 출력
+ **sytle 속성**을 통해 태그를 꾸밀 수 있음
+ **HTML Form** : 다음 태그들을 통해 사용자가 입력한 내용을 수집하고 데이터 처리를 위해 서버로 전송 **(\<form\>, \<input\>, \<select\>, \<label\>, \<button\>, etc)**
    + action 속성 : 사용자가 입력한 내용을 제출할 서버의 위치
    + method 속성 : 사용자가 입력한 내용을 제출할 때 사용할 HTTP 의 메소드 지정
    + enctype 속성 : 사용자가 입력한 내용을 제출할 때 Encoding 방법 지정 (주로 파일 업로드 기능 수행 시)
+ **[HTML 태그 및 속성 참고 사이트](https://www.w3schools.com/html/)**
---
### 2. CSS
1. **CSS Selector**
    + HTML 문서의 스타일 및 디자인을 지정하는데 사용
    + 사용 형태
      ```html
      <style>
          h2 { color: purple; }                   <!-- 태그에 스타일 지정 -->
          .class { color: white; }                <!-- 태그 class에 스타일 지정 -->
          #id { color: red; font-size: 2rem; }    <!-- 태그 id에 스타일 지정 -->
      <\style>
      ```
    + **[CSS 선택자 참고](https://hyeok999.github.io/2019/09/23/CSS-Selector-Organize/)**
    + **[CSS 가상 요소 및 가상클래스](https://velog.io/@helloworlddddd/CSS-%EA%B0%80%EC%83%81-%EC%84%A0%ED%83%9D%EC%9E%90-%EA%B0%80%EC%83%81-%ED%81%B4%EB%9E%98%EC%8A%A4)**
2. **CSS Box Model**
   +  표준 : **Width, Height, Padding, Border**를 통해 Content Box의 크기를 결정
   +  대체 : **Width, Height**만을 통해 Content Box의 크기를 결정<br>
   (By `box-sizing: border-box`)
   + 크기 지정 : `width, height, max-width, max-height, min-width, min-height`
   + 여백 지정 : `margin, padding (top / right / bottom / left)`
   + 테두리 : `border, border-radius`
   + 배경 : `background (color / image / size / position / repeat / attachment)`
3. **CSS Layout**
   + HTML 요소 간의 위치 및 부모 컨테이너와 비례한 위치 등을 제어
   + **display** : 부모 크기 및 내용물 크기
       + **block** : 요소의 크기를 **부모의 크기**대로 맞춤 (개별 행에 위치)
       + **inline-block** : 요소의 크기를 **내용물의 크기**대로 맞춤 (크기 조절 가능)(동일 행에 위치)
   + **position** : 요소가 배치되는 방식
       + static : 기본, 흐름대로 배치
       + relative : static의 배치 방식 \+ 배치된 위치에서 부터 이동 가능
       + absolute : **HTML 문서** 기준 절대 좌표 배치
       + fixed : **사용자 브라우저** 기준 절대 좌표 배치
       + sticky : static의 배치 방식 \+ fixed의 위치 고정 방식
   + grid : 표 형태 \+ 위치 및 크기 명시
   + **flexbox**
       + `display : flex;`
       + justify-content : x축 요소 정렬
       + align-items : y축 요소 정렬
4. 기타
    + [문자 스타일 제어](https://www.w3schools.com/css/css_font.asp)
    + [Overflow](https://www.w3schools.com/css/css_overflow.asp) : 요소의 내용이 지정된 영역보다 큰 경우 내용과 스크롤바 사용 여부 결정
    + z-index : 요소가 출력 순서 지정 (큰 값을 가질 수록 가장 앞에서 출력됨)
    + Viewport : 컴퓨터나 스마트폰의 브라우저 화면 크기 설정<br>
    `<meta name="viewport" content="width=device width, initial scale=1.0">`
---
### 3. Javascript
1. 개요 및 출력
   + **\<script\> 태그**에 코드를 작성하거나 **외부 파일**에 작성 후 불러온다.
   + `document.getElementById()` or `document.querySelector()`를 통해 **요소 선택 가능**
   + 출력 코드 : `innerHTML`, `document.write()`, `window.alert()`, `console.log()`
2. 자료형
   + **변수 선언**
       + var : 재선언 O, 재할당 O (전역 및 함수 범위)
       + let : 재선언 X, 재할당 O (블록 범위)
       + const : 재선언 X, 재할당 X (블록 범위)
       + *Hoisting* : 변수와 함수 선언이 맨 위로 이동
   + **변수 타입**
       + 기본 : number, string, boolean, null, defined
       + 참조 : { } (object), [ ] (list), class
       + 함수 : First-class Function **(변수로써 다루어짐)**
3. 연산자 및 제어문(조건문, 반복문)
    + 기초적인 내용으로써 생략
    + 비교 연산자
        + `==` : 두 변수의 **값**을 비교
        + `===` : 두 변수의 **값과 타입**을 비교 
4. 함수
   + 함수의 기본 형태
     ```javscript
         function name(param1, param2) {
            // body ...
            return;
        }
     ```
    +  함수의 매개변수 기본값 지정 : `function func(arg, from='unknown')`
    +  함수의 가변인자 : `function func(...args)`
    +  **First-class Function (일급 함수) : 함수가 변수처럼 다루어 진다.**
        + 함수를 변수에 값으로 할당 가능
        + 함수를 다른 함수의 인자로 사용 가능
        + 다른 함수를 함수의 반환 값으로 사용 가능
    + **Callback** : 일정 시간 경과 후 동작 (ex. `setTimeout(콜백 함수, ms)`)
    + **함수 작성 형태**
        + 선언식 : `function func() {};`
        + 표현식 : `const func = function() {};`
            + Arrow Function : `const func = () => { //logic }`
    + *Closure : 외부 변수를 기억하고 이 외부 변수에 접근할 수 있는 함수를 의미*
      ```javascript
          function sequence() {
              let seq = 0;
              return function () {
                  return ++seq;
              }
          }

          const seq = sequence();
          console.log(seq()); // output : 1
          console.log(seq()); // output : 2
          console.log(seq()); // output : 3
      ```
5. 클래스 및 객체
    + 클래스 : 프론트엔드에서 중요하게 사용되지 않음
      ```javascript
      class Person {
        name; // 속성 (field)
        age; // 속성 (field)
        speak() {} // 행동 (method)
      }
      ```
    + **객체 : `{"key":value}`**
        + Read : `obj.key` or `obj['key']` (객체의 **키 값**을 통해 접근)
        + 프론트엔드에서는 json을 통해 객체를 전송 받아 출력만 하면 되므로 Create, Update, Delete는 사용 X
        + `in` 을 통해 **속성 존재 여부 확인** 후 처리 (오류를 피하기 위함)
        + 반복문을 통해 속성 제어
          ```javascript
          for(const key in obj) {
             console.log(key, obj[key]);
          }
          ```
        + `=` : 같은 객체를 참조 --> `assign()`을 통해 객체를 복사
        + [export & import 를 통해 js 파일의 함수에 접근](https://ko.javascript.info/import-export)
6. 배열
    + [반복문을 이용한 요소 제어](https://www.w3schools.com/js/js_array_iteration.asp)
    ```javascript
        // 1. for ... in
        for (const key in animals) { console.log(animals[key]) }
    
        // 2. for ... of
        for (const value of animals) { console.log(value) }
    
        // 3. forEach
        animals.forEach(function(animals, index, array) { console.log(`${} | ${} | ${}'); });
        animals.forEach((animals, index, array) => { console.log(`${} | ${} | ${}'); });
    ```
    + [Array 자료](https://www.w3schools.com/js/js_arrays.asp)
        + [Array Method](https://www.w3schools.com/js/js_array_methods.asp)
        + [Array Search](https://www.w3schools.com/js/js_array_search.asp)
        + [Array Sort](https://www.w3schools.com/js/js_array_sort.asp)
