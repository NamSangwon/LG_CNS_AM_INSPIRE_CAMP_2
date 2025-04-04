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
  

  //                 [흐름]
  // App     ==>      List        => Item1
  //  (변화된 todos 전송)          => Item2 
  //                              => Item3 (변경된 아이템만 재렌더링)
  //                              => ...
  //                              => Item N

  // 함수가 필요할 때마다 다시 함수를 새로 생성하는 것이 아닌
  // 필요할 때마다 메모리에서 가져와서 재사용 (함수 캐싱)
  
  // 간단히 [useCallback과 useMemo(React.memo)를 같이 사용하여 불필요한 연산 피함]
  // useCallback을 사용하지 않으면, 해당 함수를 사용할 때 마다 새로 생성하여 props가 변경되기 때문에 모든 TodoListItem을 재렌더링함
  // [](의존성 배열)가 비어 있기 때문에 컴포넌트가 생성될 때만 한 번 실행됨
  const onInsert = useCallback((text) => {
    const todo = {id: nextId.current, text: text, checked: false};
    setTodos( todos => [...todos, todo] );
    nextId.current++;
  }, []);

  const onRemove = useCallback((id) => {
    setTodos(todos => todos.filter((todo) => todo.id !== id));
  }, []);

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