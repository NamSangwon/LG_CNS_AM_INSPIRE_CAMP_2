import { useCallback, useMemo, useRef, useState } from 'react';
import './App.css';
import TodoInsert from './components/TodoInsert';
import TodoList from './components/TodoList';
import TodoTemplate from './components/TodoTemplate';

function App() {
  console.log('App 생성');

  const create = useMemo(() => {
    const list = [];
    for(let i = 1; i <= 5000; i++) {
      list.push({id: i, text: `리액트 ${i}`, checked: i % 4 ? false : true})
    }
    console.log('리스트 생성');
    return list;
  }, []);
  const [todos, setTodos] = useState(create);

  const nextId = useRef(todos.length + 1);

  const onInsert = (text) => {
    const todo = {id: nextId.current, text: text, checked: false};
    setTodos( todos => [...todos, todo] );
    nextId.current++;
  };

  // 다시 렌더링하면서 todos 변화하기 때문에 함수가 실행됨
  //      => 항상 모든 TodoListItem을 필터링 => useCallback을 통해 해결
  // [](의존성 배열)가 비어 있기 때문에 컴포넌트가 생성될 때만 한 번 실행됨
  const onRemove = useCallback((id) => {
    setTodos(todos => todos.filter((todo) => todo.id !== id));
  }, []);

  // 다시 렌더링하면서 todos 변화하기 때문에 함수가 실행됨
  //     => 항상 모든 TodoListItem을 새로 생성 => useCallback을 통해 해결
  // [](의존성 배열)가 비어 있기 때문에 컴포넌트가 생성될 때만 한 번 실행됨
  const onToggle = useCallback((id) => {
    setTodos(todos => 
      todos.map((todo) => 
        todo.id === id ? { ...todo, checked: !todo.checked } : todo
      )
    );
  }, []);

  return (
    <TodoTemplate>
      <TodoInsert onInsert={onInsert} />
      <TodoList todos={todos} 
                onRemove={onRemove} 
                onToggle={onToggle} />
    </TodoTemplate>
    
  );
}
export default App;