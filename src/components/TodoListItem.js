import { MdCheckBoxOutlineBlank, MdRemoveCircleOutline, MdCheckBox } from 'react-icons/md';
import styles from './TodoListItem.module.css';
import React from 'react';

// props가 이전과 동일한 값이면 재렌더링 X
// props가 이전과 다른 값이면 재렌더링 O => 컴포넌트 재생성 후 반환
// 컴포넌트 안에서 구현한 state가 변경 => 재렌더링

// TodoListItem에서는 
//      1. props.todo가 변경됨에 따라서 재렌더링됨
//      2. onToggle, onInsert를 통해 변경 및 생성된 TodoListItem 만을 재렌더링함.
//        (onToggle인 경우 -> props.todo.checked가 변경되기 때문에 컴포넌트를 재생성 후 반환하여 재렌더링)
//        (onRemove인 경우 -> 해당 컴포넌트가 삭제되기 때문에 상관 X)
//        (onInsert인 경우 -> 해당 컴포넌트가 생성되기 때문에 첫 렌더링)
const TodoListItem = (props) => {
  console.log('TodoListItem 생성');
  
  const { id, text, checked } = props.todo;
  return (
    <div className={styles.TodoListItem}>

      <div className={checked ? `${styles.checkbox} ${styles.checked}` : styles.checkbox}
           onClick={() => props.onToggle(id)}>
        {checked ? <MdCheckBox /> : <MdCheckBoxOutlineBlank />
      }
        <div className={styles.text}>{text}</div>
      </div>

      <div className={styles.remove} onClick={() => {
        props.onRemove(id);
      }}>
        <MdRemoveCircleOutline></MdRemoveCircleOutline>
      </div>
    </div>
  );
};

// React.memo 를 통해 컴포넌트를 재렌더링하지 않고 마지막으로 렌더링된 결과를 재사용
//    props가 이전과 동일한 값이면 재렌더링하지 않고, 다른 값이면 재렌더링하여 컴포넌트를 다시 만들어 반환한다.
//    React.memo에 쓰인 컴포넌트 안에서 구현한 state가 변경되면 컴포넌트는 재렌더링이 된다.
export default React.memo(TodoListItem);