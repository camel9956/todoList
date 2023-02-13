package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

	public Iterable<Todo> createTodo(Todo todo){
		//設定日期格式模板
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//設定時區
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		//轉成字串
		String date = df.format(new Date());
		todo.setCreateTime(date);
		todo.setUpdateTime(date);
		todoDao.save(todo);
		return getTodo();
	}
	
	public Todo updateTodo(Integer id,Todo todo) {
		try {
			Todo resTodo = todoDao.findById(id).get();
			Integer status = todo.getStatus();
			resTodo.setStatus(status);
			return todoDao.save(todo);			
		} catch (Exception e) {
			return null;
		}
	}
	
	public Boolean deleteTodo(Integer id) {
		try {
			todoDao.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
