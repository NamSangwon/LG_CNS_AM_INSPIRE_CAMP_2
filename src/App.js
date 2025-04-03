import logo from './logo.svg';
import './App.css';

import { useState, useEffect } from 'react'

function App() {
  const [time, setTime] = useState({
    'hours': "00",
    'minutes': "00",
    'seconds': "00"
  })

  const [location, setLocation] = useState({
    'name': "",
    'lat': 0,
    'lon': 0
  })

  const [weatherInfo, setWeatherInfo] = useState({
    'main': '',
    'icon': '',
    'temp': 0,
    'speed': 0
  })

  function getClock() {
    const date = new Date();

    // const hours = String(date.getHours()).padStart(2, "0");
    // const minutes = String(date.getMinutes()).padStart(2, "0");
    // clock.innerText = `${hours}:${minutes}`;
    setTime({
      'hours': String(date.getHours()).padStart(2, "0"),
      'minutes': String(date.getMinutes()).padStart(2, "0"),
      'seconds': String(date.getSeconds()).padStart(2, "0")
    });
  }

  const getData = async () => {
    const url = 'https://api.openweathermap.org/data/2.5/weather?lat=35.1176&lon=129.0451&units=metric&appid=6edee3c2aa182bc44d18ccb204c98a31'
    const res = await fetch(url);
    const data = await res.json();
    console.log(data);

    // const lat = data.coord.lat;
    // const lon = data.coord.lon;
    // const name = data.name;
    // document.querySelector('#location').innerHTML = `${name} / ${lat} / ${lon}`;
    setLocation({
      'name': data.name,
      'lat': data.coord.lat,
      'lon': data.coord.lon
    });

    const icon = `http://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png`;
    // const temp = data.main.temp;
    // const speed = data.wind.speed;
    // const main = data.weather[0].main;

    // document.querySelector('#weather > span:nth-child(1)').innerHTML = `${main}`;
    // document.querySelector('#weather > span:nth-child(2)').innerHTML = `<img src='${icon}'>`;
    // document.querySelector('#weather > span:nth-child(3)').innerHTML = `${temp.toFixed(1)}℃`;
    // document.querySelector('#weather > span:nth-child(4)').innerHTML = `${speed}m/s`;

    setWeatherInfo({
      'main': data.weather[0].main,
      'icon': icon,
      'temp': data.main.temp,
      'speed': data.wind.speed
    })
  }

  useEffect(() => {
    getClock();
    setInterval(getClock, 1000);
  }, []);

  useEffect(() => {
    getData();
  }, [location, weatherInfo]);

  return (
    <div className="App">
      <h2 id="clock">{`${time.hours}:${time.minutes}:${time.seconds}`}</h2>
      <h2 id="location">{ `${location.name} / ${location.lat} / ${location.lon}` }</h2>
      <div id="weather">
        <span>{weatherInfo.main}</span>
        <span><img src={weatherInfo.icon} /></span>
        <span>{` ${weatherInfo.temp.toFixed(1)} ℃ `}</span>
        <span>{` ${weatherInfo.speed} m/s `}</span>
      </div>
    </div>
  );
}

export default App;
