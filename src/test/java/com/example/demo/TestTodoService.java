package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dao.TodoDao;
import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;

@SpringBootTest
public class TestTodoService {
	
	@Autowired
	TodoService todoService;
	
	@MockBean
	TodoDao todoDao;
	
	@Test
	public void testGetTodos() {
		List<Todo> expectdTodoList = new ArrayList<>();
		Todo todo = new Todo();
		todo.setId(1);
		todo.setTask("洗衣服");
		todo.setStatus(1);
		expectdTodoList.add(todo);
		
		//Mockito.when( 對象.方法名() ).thenReturn( 自定義結果 )
		Mockito.when(todoDao.findAll()).thenReturn(expectdTodoList);
		// [Act]操作todoService.getTodos();
		Iterable<Todo> actualTodoList = todoService.getTodo();
		// [Assert] 預期與實際的資料
		assertEquals(expectdTodoList, actualTodoList);
	}
	
	@Test
	public void testCreateTodos() {
		Todo todo = new Todo();
		todo.setId(1);
		todo.setTask("看書");
		todo.setStatus(1);
		//Mockito.when(對象方法名).thenReturn()
		Mockito.when(todoDao.save(todo)).thenReturn(todo);
		Integer actualId = todoService.createTodo(todo);
		assertEquals(1, actualId);
	}
	
	//update成功，找不到id，發生例外
	@Test
	public void testUpdateSuccess() {
		Todo todo = new Todo();
		todo.setId(1);
		todo.setTask("洗碗");
		todo.setStatus(1);
		Optional<Todo> resTodo = Optional.of(todo);
		//Mockito.when( 對象.方法名() ).thenReturn( 自定義結果 )
		Mockito.when(todoDao.findById(1)).thenReturn(resTodo);
		todo.setStatus(2);
				// [Act]操作todoService.getTodos();
		Boolean actualTodoList = todoService.updateTodo(1, todo);
				// [Assert] 預期與實際的資料
		assertEquals(true, actualTodoList);
	}
	
	@Test
	public void testUpdateNotExist() {
		Todo todo = new Todo();
		todo.setStatus(1);
		//Mockito.when( 對象.方法名() ).thenReturn( 自定義結果 )
		Mockito.when(todoDao.findById(100)).thenReturn(Optional.empty());
		// [Act]操作todoService.getTodos();
		Boolean actualTodoList = todoService.updateTodo(100, todo);
		// [Assert] 預期與實際的資料
		assertEquals(false, actualTodoList);
	}
	
	@Test
	public void testUpdateOccurException() {
		Todo todo = new Todo();
		todo.setId(1);
		todo.setTask("洗碗");
		todo.setStatus(1);
		Optional<Todo> resTodo = Optional.of(todo);
		//Mockito.when( 對象.方法名() ).thenReturn( 自定義結果 )
		Mockito.when(todoDao.findById(1)).thenReturn(resTodo);
		todo.setStatus(2);
		doThrow(NullPointerException.class).when(todoDao).save(todo);
				// [Act]操作todoService.getTodos();
		Boolean actualTodoList = todoService.updateTodo(100, todo);
				// [Assert] 預期與實際的資料
		assertEquals(false, actualTodoList);
	}
}
