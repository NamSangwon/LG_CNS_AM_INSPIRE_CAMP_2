import TodoListItem from './TodoListItem';
import styles from './TodoList.module.css';
import React from 'react';

// TodoListItem의 리스트 아이템 삭제(onRemove)와 리스트 아이템 체크(onToggle) 및 리스트 아이템 추가(onInsert)을 실행하면
// TodoList의 props.todos가 변화하기 떄문에 새로 생성됨
const TodoList = ({todos, onRemove, onToggle}) => {
  console.log('TodoList 생성');
  
  return (
    <div className={styles.TodoList}>
      {
        todos.map(
          todo => 
            <TodoListItem todo={todo} key={todo.id} 
                          onRemove={onRemove}
                          onToggle={onToggle} />
        )
      }
    </div>
  );
};

export default TodoList;