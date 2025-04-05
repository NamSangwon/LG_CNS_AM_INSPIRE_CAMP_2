import './App.css';
import styles from './components/MovieList.module.css';
import MovieList from './components/MovieList';
import MovieSearch from './components/MovieSearch';

import { useState, useCallback } from 'react'

function App() {
  const API_KEY = "";
  const [movieList, setMovieList] = useState([]);

  const getMovieData = async (keyword) => {
    const url = `https://api.themoviedb.org/3/search/movie?api_key=${API_KEY}&query=${keyword}`;
    const response = await fetch(url);
    const movieData = await response.json();

    if (movieData.results.length != 0)
      setMovieList(movieData.results);
  }

  const onSearch = useCallback((searchKeyword) => {
    getMovieData(searchKeyword);
  }, []);

  return (
    <div className="App">
      <div className={styles.movie}>
        <h1>영화 검색</h1>
        <MovieSearch onSearch={onSearch} />
        <MovieList movieList={movieList} />
      </div>
    </div>
  );
}

export default App;
