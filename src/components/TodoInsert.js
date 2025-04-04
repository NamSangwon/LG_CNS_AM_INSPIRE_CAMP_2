import { MdAdd } from 'react-icons/md';
import styles from './TodoInsert.module.css';
import React, { useCallback, useState } from 'react';

// props가 이전과 동일한 값이면 재렌더링 X
// props가 이전과 다른 값이면 재렌더링 O => 컴포넌트 재생성 후 반환
// 컴포넌트 안에서 구현한 state가 변경 => 재렌더링 

// [TodoInsert에서는 value가 변경됨에 따라 재렌더링됨]
const TodoInsert = ({onInsert}) => {
  console.log('TodoInsert 생성');
  
  const [value, setValue] = useState('');
  
  const onChange = (e) => {
    setValue(e.target.value);
  };
  
  return (
    <form className={styles.TodoInsert} onSubmit={(e) => {
      e.preventDefault();
      onInsert(value);
    }}>
      <input className={styles.input}
        placeholder="할 일을 입력하세요" 
        onChange={onChange} value={value} />

      <button type="submit" className={styles.button}>
        <MdAdd />
      </button>
    </form>
  );
};

export default React.memo(TodoInsert);