# 2주차 학습 내용

#### 1. AJAX
  + 자바스크립트로 서버와 통신하는 방법
    ```
      // 비동기적으로 서버에 요청을 보내 response를 받아 오는 함수
      async function run() {
        const res = await fetch(url); // (1)
        const data = await res.json(); // (2)
        // ...
      }
    ```
    + **async**
      + function 앞에 위치
      + 항상 `Promise 객체`를 반환 (`Promise`가 아닌 값을 `return` 시, `Promise`로 감싸 반환)
    + **await**
      + `async 함수` 안에서만 동작
      + `Promise`가 처리될 때까지 대기
    + **Promise**
      + 자바스크립트 비동기 처리에 사용되는 객체
      + 서버에서 받아온 데이터를 화면에 표시할 때 사용
      + `Promise`의 처리 과정을 3가지 상태(Pending(대기), Fulfilled(이행), Rejected(실패))로 표현
    + **`run()`**
      + (1) `fetch()`를 통해 서버에서 `response`를 받는다.
      + (2) `fetch()`를 마치면, `response` 내의 `json()`를 통해 받아 온 데이터를 json 형식으로 변환한다.

#### 2. DOM
  + Document Object Model
  + HTML의 Tag를 자바스크립트에서 이용할 수 있도록 객체로 만듦
  + 문서 객체 가져오는 방법 : `document.head`, `document.body`, `document.querySelector('CSS 선

#### 3. Event
  + [이벤트 종류](https://ko.javascript.info/introduction-browser-events)
  + `preventDefault()` : 태그가 가지는 기본 이벤트를 막음 (ex. `a 태그`, `form의 submit`)
  + `stopPropagation()` : 연쇄 작용 방지 (ex. `<div><p></p></div>`의 `<div>`의 이벤트가 `<p>`에 적용되는 것을 방지)

#### 4. Browser Storage
  + 자바스크립트에 의해 사용자의 PC(브라우저)에 저장되는 파일 (ex. 사용자 로그인 정보)
  + **Cookie**
    + 문자열만 저장 가능
    + Session Cookie : 브라우저 종료 시 쿠키 삭제
    + Persistent Cookie : *만료일자를 지정*하여 해당 일자에 삭제
    + 웹 서버(Backend)에서 활용 (ex. JWT)
  + **Local Storage**
    + 객체 저장 가능
    + 자동 삭제 X
    + 대용량 및 객체 활용
  + **Session Storage**
    + 객체 저장 가능
    + 브라우저 및 탭 종료 시 삭제
    +  데이터 임시 활용
   
#### 5. React 기초
  + 특징
    + **가상 DOM** : UI를 변경해야 할 때, 전체 DOM을 업데이트 하는 대신 가상 DOM을 사용하여 필요한 부분만 업데이트
    + **컴포넌트**
      + 전체 DOM을 작게 가상 DOM으로 나누어 재사용 가능하도록 변경
      + 자바스크립트의 함수와 비슷하다 (입력 : Props & 출력 : React Element)
      + `함수 컴포넌트`와 `클래스 컴포넌트`가 존재 (함수 컴포넌트를 더 자주 사용)
      + 이름 : 소문자는 HTML의 태그로 인식하기 때문에 항상 *대문자*로 시작
    + JSX : XML과 유사한 문법으로서, React 요소를 쉽게 작성
  + 예시 코드
  ```
    // Props = name, content
    function Header({name, content}) {
      return (
        <header>
          <h1>{name}</h1>
          {content}
        </header>
      )
    }
    
    function Nav() {
      return (
        <nav>
          <ul>
            <li><a href='1.html'>HTML</a></li>
            <li><a href='2.html'>CSS</a></li>
            <li><a href='3.html'>JavaScript</a></li>
          </ul>
        </nav>
      )
    }

    // 컴포넌트 합성 : 여러 개의 컴포넌트를 합쳐서 하나의 컴포넌트로 만듦 
    // 컴포넌트 추출 : 복잡한 컴포넌트를 여러 개의 컴포넌트로 나눔
    function App() {
      return (
        <div className="App">
          <Header name="WEB" content="World Wide Web!" />
          <Nav />
        </div>
      );
    }
  ```
+ **Props**
  + `부모 컴포넌트`에서 `자식 컴포넌트`로 전달하는 데이터 (단방향)
  + 함수로 전달되는 인자와 같은 개념
  + `읽기 전용` : 값 변경 불가
  + `키`와 `값`으로 이루어진 형태로 컴포넌트에 전달
  + `Props 값`을 변경 :  부모 컴포넌트에서 변경된 값을 전달 &rarr; 자식 컴포넌트를 다시 렌더링
  + ex) `<Header name="WEB" /> => function Header({name}) or function Header(props)`
+ **State**
  + 컴포넌트 안에서 관리되는 데이터 (`useState()를 통해 생성`)
  + 컴포넌트가 다시 렌더링 되어도 `state 값`을 유지
  + 변경 가능 : 변경된 상태를 업데이트하기 위해서 `setState()` 사용
  + `state 값`이 변경되면 해당 컴포넌트를 다시 렌더링 (자식 컴포넌트도 같이 렌더링함)
  + ex) `const [list, setList] = useState([]); // 빈 배열로 초기화`
+ **이벤트 적용**
  1. 이벤트 핸들러 함수 정의
  2. 이벤트 핸들러 연결
  3. (필요 시) 이벤트 객체 사용
  ```
    // 1. 이벤트 핸들러 함수 정의
    // 3. 이벤트 객체(e) 사용 
    const onClick = (e) => {
      alert(`${e.target.innerText}`);
    };
  
    function Header(props) {
      return (
        <header>
          <h1 onClick={onClick}>{props.title}</h1> {/* 2. 이벤트 핸들러 연결 */}
          {props.content}
        </header>
      );
    }
  ```
