package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;
	
	
	@GetMapping("/todos")
	public String getTodos(Model model) {
		Iterable<Todo> todolist = todoService.getTodo();
		model.addAttribute("todolist",todolist);
		Todo todo = new Todo();
		model.addAttribute("todoObject",todo);
		return "todolist";
	}
	
	@PostMapping("/todos")
	public String createTodo(@ModelAttribute Todo todo,Model model) {
		Iterable<Todo> newTodoList = todoService.createTodo(todo);
		Todo emptyTodo = new Todo();
		model.addAttribute("todolist",newTodoList);
		model.addAttribute("todoObject",emptyTodo);
		return "todolist";
	}

	@ResponseBody
	@PutMapping("/todos/{id}")
	public void updateTodo(@PathVariable Integer id,@RequestBody Todo todo) {
		todoService.updateTodo(id, todo);
	}

	@ResponseBody
	@DeleteMapping("/todos/{id}")
	public void deleteTodo(@PathVariable Integer id) {
		todoService.deleteTodo(id);
	}
	
	
}
