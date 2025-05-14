import { useState } from 'react'

import 'bootstrap/dist/css/bootstrap.min.css';

import Header from './components/Header'
import Nav from './components/Nav'
import Index from './pages/Index'
import Footer from './components/Footer'
import Signin from './pages/Signin';

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Header/>
      <Nav/>
      {/* <Index/> */}
      {/* <Signup/> */}
      <Signin/>
      <Footer/>
    </>
  )
}

export default App
