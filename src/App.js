import { useRef, useState } from 'react';
import './App.css';

const App = () => {
  const password_match = useRef();
  const [agree, setAgree] = useState(false);
  const [userInfo, setUserInfo] = useState({
    userId : "",
    password : "",
    confirmPassword : "",
    email : "",
    phoneNumber : "",
    address : ""
  });

  // 객체를 JSON 문자로 변환
  // JSON.stringfy(userInfo);

  // input 값에 대한 공용 함수
  const handleChange = (event) => {
    const name = event.target.name; // form input tag name
    const value = event.target.value;

    // 원래 값 가져온 후 name 키의 값을 업데이트
    const newUserInfo = {...userInfo, [name] : value};
    setUserInfo(newUserInfo); 

    const pw = newUserInfo.password;
    const cpw = newUserInfo.confirmPassword;

    // style.display -> !important에 우선순위 밀림
    if (pw == cpw)
      // password_match.current.style.display = 'none';
      password_match.current.classList.remove('pw-match');
    else
      // password_match.current.style.display = 'inline-block';
      password_match.current.classList.add('pw-match');
  }

  const showInfo = () => {
    alert(`
      [입력된 내용]
      아이디 : ${userInfo.userId}
      비밀번호 : ${userInfo.password}
      이메일 : ${userInfo.email}
      전화번호 : ${userInfo.phoneNumber}
      주소 : ${userInfo.address}
    `);
  }

  // [콜백 함수를 사용하는 방법이 더 좋음]
  // setShow(!show) : 중간에 바뀐 show 값에 대해서 ! 적용
  // setShow(prev => !prev) : 렌더링 시의 show 값에 대해서 ! 적용

  return (
    <div className="container">
      <h2>회원 가입</h2>
      <form>
        <div className="form-group">
          <input
            type="checkbox"
            id="agree"
            onChange={()=>{
              setAgree(prev => !prev)
            }}
          />
          <label className='agree' htmlFor="agree">이용약관에 모두 동의합니다</label>
        </div>

        {
          agree ? (
            <div>
              <div className="form-group">
                <label htmlFor="userId">사용자 아이디</label>
                <input
                  type="text"
                  id="userId"
                  name="userId"
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="password">비밀번호</label>
                <input
                  type="password"
                  id="password"
                  name="password"
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="confirmPassword">비밀번호 확인</label>
                <input
                  type="password"
                  id="confirmPassword"
                  name="confirmPassword"
                  onChange={handleChange}
                  required
                />
                {/* {(password == confirmPassword) ? null : <label id="pw-match" className="pw-match">비밀번호가 일치해야 됩니다.</label>} */}
                <label id="pw-match" className ref={password_match}>비밀번호가 일치해야 됩니다.</label>
              </div>

              <div className="form-group">
                <label htmlFor="email">이메일</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="phoneNumber">전화번호</label>
                <input
                  type="tel"
                  id="phoneNumber"
                  name="phoneNumber"
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="address">주소</label>
                <input
                  type="text"
                  id="address"
                  name="address"
                  onChange={handleChange}
                  required
                />
              </div>

              <button type="submit" onClick={showInfo}>제출</button>
            </div>
          ) : null
        }
      </form>
    </div>
  );

};

export default App;