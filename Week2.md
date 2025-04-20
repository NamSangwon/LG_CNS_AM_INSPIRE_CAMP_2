# 2주차 학습 내용

### 1. AJAX
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
---
### 2. DOM
  + Document Object Model
  + HTML의 Tag를 자바스크립트에서 이용할 수 있도록 객체로 만듦
  + 문서 객체 가져오는 방법 : `document.head`, `document.body`, `document.querySelector('CSS 선택자')`
---
### 3. Event
  + [이벤트 종류](https://ko.javascript.info/introduction-browser-events)
  + `preventDefault()` : 태그가 가지는 기본 이벤트를 막음 (ex. `a 태그`, `form의 submit`)
  + `stopPropagation()` : 연쇄 작용 방지 (ex. `<div><p></p></div>`의 `<div>`의 이벤트가 `<p>`에 적용되는 것을 방지)
---
### 4. Browser Storage
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
--- 
### 5. React 기초
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
  + 자바스크립트의 객체
  + `부모 컴포넌트`에서 `자식 컴포넌트`로 전달하는 데이터 (단방향)
  + 함수로 전달되는 인자와 같은 개념
  + `읽기 전용` : 값 변경 불가
  + `키`와 `값`으로 이루어진 형태로 컴포넌트에 전달
  + `Props 값`을 변경 :  부모 컴포넌트에서 변경된 값을 전달 &rarr; 자식 컴포넌트를 다시 렌더링
  + ex) `<Header name="WEB" /> => function Header({name}) or function Header(props)`
---  
#### 6. React Hook
  + 생명주기 관련 기능을 함수 컴포넌트에서도 사용할 수 있게 지원하는 기술
  + **useState** : 컴포넌트의 상태를 관리하기 위해 사용
    + 컴포넌트 안에서 관리되는 데이터 (`useState()를 통해 생성`)
    + 변수 저장 기간 : 컴포넌트가 다시 렌더링 되어도 `state 값`을 유지 (페이지 새로고침 전까지 저장)
    + 변경 가능 : 변경된 상태를 업데이트하기 위해서 `setState()` 사용
    + `state 값`이 변경되면 해당 컴포넌트를 다시 렌더링 (자식 컴포넌트도 같이 렌더링함)
    + ex)
      ```
        // 초기화
        const [count, setCount] = useState(0);

        // state 업데이트 (count 1 증가)
        setCount(count + 1);
      ```
  + **useEffect** : Side Effect를 처리하기 위해 사용 (ex. 스택 오버플로우)
    + 형태 : `useEffect(콜백 함수, 의존성 배열);` (의존성 배열에는 state 변수를 지정)
    + 특정 상황에만 함수(부가적인 작업)를 실행하도록 하여 효율성 높임
    + **컴포넌트 최초 생성**, **의존성 배열 내의 state 변수 변경** 및 **컴포넌트 제거** 시에, 콜백 함수 동작
    + 의존성 배열이 빈 배열이라면, 컴포넌트가 생성될 때만 콜백 함수 동작
    + 의존성 배열이 빈 배열이고 콜백 함수를 반환 시, 컴포넌트가 제거될 때만 콜백 함수 동작 
  + **useMemo** : 컴포넌트가 다시 렌더링 될 때, 연산량이 많은 작업이 반복되는 것을 방지하기 위해 사용 (ex. 팩토리얼 계산)
    + 형태 (의존성 배열에는 state 변수를 지정)
      ```
        const memoizedValue = useMemo (()
          // 많은 작업을 수행하는 코드
          return 결과
        }, [state1 , state2 , ...]);
      ```
    + 이전에 계산한 값을 메모리에 저장하여 동일한 계산의 반복 수행을 제거
    + 콜백 함수에서 계산 후 반환된 **값을 기억** (값을 반환)
    + 렌더링이 수행되는 도중에 동작
    + **컴포넌트 최초 생성**, **의존성 배열 내의 state 변수 변경** 시에, 콜백 함수 동작
    + 의존성 배열이 빈 배열이라면, 컴포넌트가 생성될 때만 콜백 함수 동작
  + **useCallback** : 컴포넌트가 다시 렌더링 될 때, 연산량이 많은 작업이 반복되는 것을 방지하기 위해 사용 (ex. *함수형 props 변수*로써 자식 컴포넌트로 전송)
    + 형태 : `const (function_variance) = useCallback(콜백 함수, 의존성 배열)` (의존성 배열에는 state 변수를 지정)
    + 호출 시, 작업을 수행하기 위해 작성된 **콜백 함수를 기억**
    + 렌더링이 수행되는 도중에 동작
    + **컴포넌트 최초 생성**, **의존성 배열 내의 state 변수 변경** 시에, 콜백 함수 동작
    + 의존성 배열이 빈 배열이라면, 컴포넌트가 생성될 때만 콜백 함수 동작
  + **useRef**
    + 형태 : `const (variance) = useRef()`
    + 컴포넌트의 특정 상태에 직접 접근할 수 있는 객체
    + 값이 변경되도 다시 렌더링 X
    + 사용 목적
      + 저장 공간 관리 (ex. 렌더링 하여도 초기화되지 않는 변수)
      + **DOM 요소에 접근**
        ```
          function Counter() {
            const divRef = useRef();
            const onClick = () => {
              console.log(divRef.current.innerHTML); // div 태그에 작성된 값에 접근
            }
            return (
              <div ref={divRef}>버튼 클릭 시, 해당 글이 로그에 찍힘.</div>
              <button onClick={onClick}>Click</button>
            )
          }
        ```
  + **React.memo** : 자식 컴포넌트와 무관하게 **state 변경 시**, 자식 컴포넌트를 **다시 렌더링 하지 않기 위함**
    + 형태 : `const (component) = React.memo(컴포넌트의 return value);`
    + 마지막으로 렌더링된 컴포넌트를 기억
    + 컴포넌트를 재렌더링하지 않고 마지막으로 렌더링된 결과를 재사용
    + 전달 받은 props가 이전과 동일한 값이면 재렌더링하지 않고, 다른 값이면 재렌더링하여 컴포넌트를 다시 만들어 반환
    + 해당 컴포넌트 안에서 구현한 state가 변경되면, 재렌더링
---
### 7. React Event
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
  + Argument 전달 방법
    ```
      // click()를 호출할 수 있도록 익명 함수 내에서 실행하도록 함.
      // onClick={click(5)} 과 같이 사용하면, click(5)이 즉시 실행되어, 결과 값이 전달된다.
      function MyButton(props) {
        const click = (number) => console.log(`버튼 클릭: ${number}`);
    
        return (
          <button onClick={() => click(5)}>버튼</button> 
        );
      }
    ```
---
### 8. React 조건부 렌더링
  + 아래의 방법들을 통해 렌더링을 달리함
    + `if 문`
    + `삼항 연산자` 
    + `인라인 조건` (&& 논리 연산자 사용) ((condition) && (expression) : condition이 만족되면 expression 실행)
  + 컴포넌트를 렌더링하지 않으려면 `null`을 리턴
---
### 9. 데이터 요청
  + Request (Client to Server) : **GET**, **POST**, PUT, DELETE (CRUD)
    + 3가지 요청 데이터 형태
      + Query Parameter (GET, POST) : ex) `/req/get/?desc=xyz&page=3&title=qwe`
      + Form Data (Only POST) : ex) `/req/post/`
      + JSON Data (Only POST) : ex) `/req/post/`
  + Response (Server to Client) : HTML, JSON, ...
  + [AJAX (Asynchronous Javascript And XML)](https://blog.naver.com/tex02/223268631883) : JavaScript Old API, **JavaScript New API**, JQuery, **Axios**
---
### 10. React 스타일링
  + 3가지 방식
    + Inline : React는 컴포넌트를 통해 가상 DOM을 관리하기 때문에 inline 방식으로 style을 관린하기 좋음
      + `<div style={{border: '1px solid red'}}>...</div>` 
    + module-css : 태그에 className을 통해 적용
      ```
        // [App.module.css]
        .border {
            border: 1px solid red;
        }
        ----------------------------
        import styles from './App.module.css';
      
        function Nav() {
          return (
            <div className={styles.border}>...</div>
          )
        }
      ``` 
    + styled-components : Library
      ```
        import styled from 'styled-components';
      
        const Button = styled.button`
          color: ${props => props.black ? 'white' : 'black'};
          background: ${props => props.black ? 'black' : 'white'};
          border: 1px solid black;
          &:hover {
          opacity: 0.8;
          }
        `;
      
        function App() {
          return (
            <div>
              <Button>Normal</Button>
              <Button black>Dark</Button>
            </div>
          );
        }
      ```
---
### 11. React Router DOM
  + Router : 사용자의 요청을 각 담당 컴포넌트로 연결하는 기능
    + `<BrowserRouter>` : 애플리케이션 전체를 감싸며 라우팅을 관리
    + `<Routes>` : 여러 개의 `<Route>`를 표현하기 위한 컴포넌트
    + `<Route>` : 특정 경로에 해당하는 컴포넌트
    + `<Link>`, `<NavLink>` : `<a> 태그`를 대신할 수 있는 컴포넌트
  + **SPA (Single Page Application)**
    + ~~`<a>` : 페이지 전체를 새로고치며 해당 링크로 이동~~
    + `<Link>` : 컴포넌트 만을 다시 렌더링 (전체 내용 중 부분 변경)
    + `<NavLink>` : `<Link>` + 태그에 `class="active"` 속성이 추가됨
    ```
      function App() {
        return (
          <BrowserRouter>
            <div>
              <h1>React Router DOM</h1>
              <Routes>
                <Route path='/1' element={<Component1 />} />
                <Route path='/2' element={<Component2 />} />
                <Route path='/3' element={<Component3 />} />
              </Routes>
            </div>
          </BrowserRouter>
        );
    }
    ```
  + **Routing Parameter**
    + Path Parameter : `URL`에 값을 포함시켜서 전달
      + ex) URL = `/users/123`, `/board/3`
      ```
        function MyComponent() {
          const { id } = useParams();
          return (<div>ID: {id}</div>);
        }
        function App() {
          return (
            <div><Route path="/detail/:id" component={MyComponent} /></div>
          );
        }
      ```
    + Request Parameter : `?` 기호를 사용하여 `Query String`으로 전달
      + ex) URL = `/users?id=123`, `/board?num=3`
      ```
        function MyComponent() {
          const location = useLocation();
          const searchParams = new URLSearchParams(location.search);
          const id = searchParams.get('id');
          const name = searchParams.get('name');
          return (<div>ID-{id}, NAME-{name}</div>);
        }
        function App() {
          return (
            <div><Route path="/detail" component={MyComponent} /></div>
          );
        }
      ```
