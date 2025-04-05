import { BrowserRouter, Routes, Route } from 'react-router-dom';
import PathNewsPage from './components/PathNewsPage';
import RequestNewsPage from './components/RequestNewsPage';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="*" element={
            <PathNewsPage />
          } 
        />
        <Route path="/news" element={
          <RequestNewsPage />
        } 
      />
      </Routes>
    </BrowserRouter>);
}

export default App;
