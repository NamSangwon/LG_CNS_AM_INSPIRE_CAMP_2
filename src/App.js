import logo from './logo.svg';
import { useState } from 'react'
import './App.css';

const MinutesToHours = () => {
  const [bInputState, setInputState] = useState(false);
  const [minuteValue, setMinuteValue] = useState();
  const [hourValue, setHourValue] = useState();

  const reset = () => {
    setInputState(false);
    setMinuteValue(0);
    setHourValue(0);
  }

  const flip = () => {
    setInputState(!bInputState);
  }

  const inputMinute = (event) => {
    const content = event.target.value;
    setMinuteValue(content);
    setHourValue(content / 60);
  }

  const inputHour = (event) => {
    const content = event.target.value;
    setHourValue(content);
    setMinuteValue(content * 60);
  }

  return (
    <div>
      <h3>Minutes To Hours</h3>
      <div>
        <label htmlFor="minutes">Minutes</label>
        <input value={minuteValue} type="number" id="minutes" placeholder="Minutes"
          disabled={bInputState} onChange={inputMinute}/>
      </div>
      <div>
        <label htmlFor="hours">Hours</label>
        <input value={hourValue} type="number" id="hours" placeholder="Hours"
          disabled={!bInputState} onChange={inputHour}/>
      </div>
      <button onClick={ reset }>Reset</button>
      <button onClick={ flip }>Flip</button>
    </div>
  )
};

function App() {
  return (
    <div className="App">
      <MinutesToHours/>
    </div>
  );
}

export default App;
