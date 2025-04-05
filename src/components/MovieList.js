import styles from './MovieList.module.css';

const MovieList = () => {
  return (
    <div className={styles.movie}>
      <h1>영화 검색</h1>
      <div className={styles.search_box}>
        <input
          type="text"
          placeholder="영화 제목을 검색하세요"
        />
        <button>
          검색
        </button>
      </div>
      <ul className={styles.list}>
        <li>
          <img src={`https://image.tmdb.org/t/p/w185/qYczuua2tgjfxcdtLNDC0n4mOHz.jpg`}
          />
          <h2>서울의 봄</h2>
          <p>평점: 7.428</p>
          <p>개봉일: 2023-11-22</p>
          <p>After the assassination of President Park, martial law has been declared.</p>
        </li>
      </ul>
    </div>
  );
};

export default MovieList;