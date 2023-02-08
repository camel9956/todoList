package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Todo;
//用在哪個table,id的型態
public interface TodoDao extends CrudRepository<Todo, Integer>  {

}
