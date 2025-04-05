import styles from './MovieList.module.css';
import MovieListItem from './MovieListItem';
import React from 'react';

const MovieList = ({ movieList }) => {
  console.log("Create MovieList");

  return (
    <ul className={styles.list}>
      {
        movieList.map((movie, index) => {
          console.log(movie);
          return <MovieListItem key={index} movieData={movie} />
        })
      }
    </ul>
  );
};

export default React.memo(MovieList);