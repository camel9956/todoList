package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TestTodoController {
	
	MockMvc mockMvc;
	ObjectMapper mapper;
	
	@Autowired
	public TestTodoController(MockMvc mockMvc, ObjectMapper mapper) {
		this.mockMvc = mockMvc;
		this.mapper = mapper;
	}

	@MockBean
	TodoService todoService;

	@Test
	public void testGetTodo() throws Exception {
		List<Todo> exceptedList = new ArrayList<>();
		Todo todo = new Todo();
		todo.setId(1);
		todo.setTask("擦嘴巴");
		exceptedList.add(todo);
		//預期資料
		Mockito.when(todoService.getTodo()).thenReturn(exceptedList);
		//操作
		String returnString = mockMvc.perform(MockMvcRequestBuilders.get("/api/todos"))
			   .andExpect(status().isOk())
			   .andReturn().getResponse().getContentAsString();
		Iterable<Todo> actualList = mapper.readValue(returnString, new TypeReference<Iterable<Todo>>() {
		});
		//比較
		assertEquals(exceptedList, actualList);
	}
	
}
