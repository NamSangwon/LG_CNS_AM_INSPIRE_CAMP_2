import styles from './MovieList.module.css';
import React from 'react'

const MovieListItem = ({movieData, index}) => {
  console.log("Create MovieListItem");

  return (
    <li key={index}>
      <img src={`https://image.tmdb.org/t/p/w185${movieData.poster_path}`} />
      <h2>{movieData.title}</h2>
      <p>{`평점: ${movieData.vote_average}`}</p>
      <p>{`개봉일: ${movieData.release_date}`}</p>
      <p>{movieData.overview}</p>
    </li>
  );
};

export default React.memo(MovieListItem);