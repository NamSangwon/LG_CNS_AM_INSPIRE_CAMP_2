import styles from './MovieList.module.css';
import {useRef} from 'react'
import React from 'react';

const MovieSearch = ({onSearch}) => {
    console.log("Create MovieSearch");

    const movieKeyword = useRef();

    return (
        <div className={styles.search_box}>
            <input
                type="text"
                placeholder="영화 제목을 검색하세요"
                ref={movieKeyword}
            />
            <button
                onClick={(e)=>{
                    e.preventDefault();
                    onSearch(movieKeyword.current.value);
                }}
            >검색</button>
        </div>
    );
};
export default React.memo(MovieSearch);