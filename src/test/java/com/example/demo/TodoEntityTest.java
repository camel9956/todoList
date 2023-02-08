package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.demo.entity.Todo;

public class TodoEntityTest {
	@Test
	public void whenGetId_ThenSetId() {
		Todo todo = new Todo();
		todo.setId(1);
		Integer expected = 1;
		Integer actual = todo.getId();
		// 比較兩者是否相等
		assertEquals(expected, actual);
	}

	@Test
	public void whenGetTask_ThenSetTask() {
		Todo todo = new Todo();
		todo.setTask("洗衣服");
		String expected = "洗衣服";
		String actual = todo.getTask();
		// 比較兩者是否相等
		assertEquals(expected, actual);
	}

	@Test
	public void whenSetTask_ThenGetTask() {
		Todo todo = new Todo();
		todo.setStatus(2);
		Integer expected = 2;
		Integer actual = todo.getStatus();
		// 比較兩者是否相等
		assertEquals(expected, actual);
	}
}
