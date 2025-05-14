import { useState } from 'react'

import 'bootstrap/dist/css/bootstrap.min.css';

import Header from './components/Header'
import Nav from './components/Nav'
import Footer from './components/Footer'
import BoardLayout from './components/BoardLayout';

import Index from './pages/Index'
import Signin from './pages/Signin';
import Signup from './pages/Signup';
import List from './pages/List';
import View from './pages/View';
import Write from './pages/Write';
import Update from './pages/Update';


import { BrowserRouter, Route, Routes } from 'react-router-dom';

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>
        <Header/>
        <Nav/>

        <Routes>
          <Route path="/" element={<Index/>}/>
          <Route path="/signup" element={<Signup/>}/>
          <Route path="/signin" element={<Signin/>}/>

          {/* Nested Route */}
          <Route path="/board" element={<BoardLayout/>}>
            <Route path="list" element={<List/>}/>
            <Route path="view" element={<View/>}/>
            <Route path="write" element={<Write/>}/>
            <Route path="update" element={<Update/>}/>
          </Route>
          
        </Routes>

        <Footer/>
      </BrowserRouter>
    </>
  )
}

export default App
