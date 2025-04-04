import './App.css';
import styles from './App.module.css';
import {useState} from 'react'

function Title() {
  return (
    <div className='title'>
      <h1>가위 바위 보 게임</h1>
    </div>
  );
}
function Scissors(props) {
  return (
    <div className={styles.control} onClick={() => {
      props.send(0);
    }}>
      <img src='http://ggoreb.com/images/react/scissors.png' />
    </div>
  );
}
function Rock(props) {
  return (
    <div className={styles.control} onClick={() => {
      props.send(1);
    }}>
      <img src='http://ggoreb.com/images/react/rock.png' />
    </div>
  );
}
function Paper(props) {
  return (
    <div className={styles.control} onClick={() => {
      props.send(2);
    }}>
      <img src='http://ggoreb.com/images/react/paper.png' />
    </div>
  );
}
function Result(props) {
  return (
    <div className='result'>
      <h1>Com:{props.result.com}</h1>
      <h1>Player:{props.result.player}</h1>
      <h1>{props.result.result}</h1>
    </div>
  );
}
function App() {
  const [gameResult, setGameResult] = useState({});

  // Such as Enum in C++
  const RPS = {
    0 : '가위',
    1 : '바위',
    2 : '보'
  }

  // 하위 컴포넌트에 '함수를 전송'하여 값을 가져오도록 함.
  const send = (rps) => {
    const com = parseInt(Math.random() * 3);

    const result = "";
    if ((rps  + 1) % 3 == com)
      setGameResult({
        com:RPS[com], 
        player:RPS[rps], 
        result:"COM WIN"
      });
    else if ((com + 1) % 3 == rps)
      setGameResult({
        com:RPS[com], 
        player:RPS[rps], 
        result:"PLAYER WIN"
      });
    else 
      setGameResult({
        com:RPS[com], 
        player:RPS[rps], 
        result:"DRAW"
      });
  }

  return (
    <div className="App">
      <Title />
      <Scissors send={send}/>
      <Rock send={send}/>
      <Paper send={send}/>
      <Result result={gameResult}/>
    </div>
  );
}

export default App;
