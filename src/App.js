import { useRef, useState } from 'react';
import './App.css';

const App = () => {
  // Convert Object for Simplify Data (생략)
  const [agree, setAgree] = useState(false);
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [address, setAddress] = useState("");

  const agreeRegister = () => {
    setAgree(!agree);
  }

  const showInfo = () => {
    alert(`
      [입력된 내용]
      아이디 : ${id}
      비밀번호 : ${password}
      이메일 : ${email}
      전화번호 : ${phoneNumber}
      주소 : ${address}
    `);
  }

  return (
    <div className="container">
      <h2>회원 가입</h2>
      <form>
        <div className="form-group">
          <input
            type="checkbox"
            id="agree"
            onChange={agreeRegister}
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
                  value = {id}
                  id="userId"
                  name="userId"
                  onChange={(event)=>{setId(event.target.value)}}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="password">비밀번호</label>
                <input
                  type="password"
                  value = {password}
                  id="password"
                  name="password"
                  onChange={(event)=>{setPassword(event.target.value)}}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="confirmPassword">비밀번호 확인</label>
                <input
                  type="password"
                  value = {confirmPassword}
                  id="confirmPassword"
                  name="confirmPassword"
                  onChange={(event)=>{setConfirmPassword(event.target.value)}}
                  required
                />
                {(password == confirmPassword) ? null : <label id="pw-match" className="pw-match">비밀번호가 일치해야 됩니다.</label>}
              </div>

              <div className="form-group">
                <label htmlFor="email">이메일</label>
                <input
                  type="email"
                  value = {email}
                  id="email"
                  name="email"
                  onChange={(event)=>{setEmail(event.target.value)}}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="phoneNumber">전화번호</label>
                <input
                  type="tel"
                  value = {phoneNumber}
                  id="phoneNumber"
                  name="phoneNumber"
                  onChange={(event)=>{setPhoneNumber(event.target.value)}}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="address">주소</label>
                <input
                  type="text"
                  value = {address}
                  id="address"
                  name="address"
                  onChange={(event)=>{setAddress(event.target.value)}}
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
