package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;

@RestController
public class TodoController {

	@Autowired
	TodoService todoService;
	
	@GetMapping("/todo")
	public Iterable<Todo> getTodoList(){
		Iterable<Todo> todolist = todoService.getTodo();
		return todolist;
	}
	
}
