import logo from './logo.svg';
import './App.css';

// State 
import {useState} from 'react'

// Comment & UserInfo Component 분리
import Comment from './Comment'

// using react-bootstrap (https://react-bootstrap.netlify.app/docs/getting-started/introduction)
import 'bootstrap/dist/css/bootstrap.min.css';

import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

function CardComponent() {
  return (
    <Card style={{ width: '18rem' }}>
      <Card.Img variant="top" src="holder.js/100px180" />
      <Card.Body>
        <Card.Title>Card Title</Card.Title>
        <Card.Text>
          Some quick example text to build on the card title and make up the
          bulk of the card's content.
        </Card.Text>
        <Button variant="primary">Go somewhere</Button>
      </Card.Body>
    </Card>
  );
}

// 첫 문자는 반드시 대문자
// 'Header' Component
function Header() {
  return (
    <header>
      <h1>WEB</h1>
      World Wide Web!
    </header>
  );
}

// 'Nav' Component
// props =  App()에서 작성한 데이터 형식으로 작성 가능 (ex. {list})
function Nav({list}) { 
  const [count, setCount] = useState(0);

  return (
    <nav>
      <button onClick={()=>{
        setCount(count + 1)
      }}>증가 {count} </button>
      <ul>
        {
          list.map((v, i)=>{
            return (
              <li key={i}>
                <a href={`${i+1}.html`}>
                  {v}
                </a>
              </li>
            )
          })
        }
      </ul>
    </nav>
  );
}

function App() {
  const list = [ 'HTML', 'CSS', 'JavaScript' ];

  return (
    // Component 사용
    <div className="App">
      
      <Header />
      <Nav list={list} /> 
     
      {/* <Comment author={
        {
          'avatarUrl' : 'http://ggoreb.com/images/img_avatar1.png',
          'name' : 'B'
        }
      } text="TEXT" date="2025-04-01"/>  
      */}
      {/* 
        <CardComponent/> 
      */}
    </div>
  );
}

export default App;
