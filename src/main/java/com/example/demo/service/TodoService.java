package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TodoDao;
import com.example.demo.entity.Todo;

@Service
public class TodoService {
	@Autowired
	TodoDao todoDao;
	
	public Iterable<Todo> getTodo(){
		return todoDao.findAll();
	}

//	public Iterable<Todo> createTodo(Todo todo){
//		//設定日期格式模板
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		//設定時區
//		df.setTimeZone(TimeZone.getTimeZone("GMT"));
//		//轉成字串
//		String date = df.format(new Date());
//		todo.setCreateTime(date);
//		todo.setUpdateTime(date);
//		todoDao.save(todo);
//		return getTodo();
//	}
//
//	public Todo updateTodo(Integer id,Todo todo) {
//		try {
//			Todo resTodo = todoDao.findById(id).get();
//			Integer status = todo.getStatus();
//			resTodo.setStatus(status);
//			return todoDao.save(todo);			
//		} catch (Exception e) {
//			return null;
//		}
//	}
//	
//	public Boolean deleteTodo(Integer id) {
//		try {
//			todoDao.deleteById(id);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
	
	
	public Integer createTodo(Todo todo) {
		Todo rltTodo = todoDao.save(todo);
		return rltTodo.getId();
	}
	
	public Optional<Todo> findById(Integer id){
		Optional<Todo> todo = todoDao.findById(id);
		return todo;
	}
	
	public Boolean updateTodo(Integer id,Todo todo) {
		Optional<Todo> isExistTodo = findById(id);
		if(!isExistTodo.isPresent()) {
			return false;
		}if(todo.getStatus() == null) {
			return false;
		}
		Todo newTodo = isExistTodo.get();
		newTodo.setStatus(todo.getStatus());
		try {
			todoDao.save(newTodo);
			return true;			
		} catch (Exception e) {
			return false;			
		}
	}

	public Boolean deleteTodo(Integer id) {
		Optional<Todo> findTodo = findById(id);
		if (!findTodo.isPresent()) {
			return false;
		}
		try {			
			todoDao.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
