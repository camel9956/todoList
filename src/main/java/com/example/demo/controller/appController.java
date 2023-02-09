package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.person;



@Controller
public class appController {
	
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("hello","Hello World");
						//( 變數名稱 ，  變數值 )
		return "hello";
	}
	
	@GetMapping("person")
	public String person(Model model) {
		model.addAttribute("gender","female");
		return "person";
	}
	
	
	@GetMapping("list")
	public String listNumber(Model model) {
		ArrayList<Object> list = new ArrayList<>();
		for (int i = 0; i <= 10; i++) {
			list.add("This is ArrayList"+i);
		}
		model.addAttribute("list",list);
		return "list";
	}
	
	@GetMapping("form")
	public String form(Model model) {
		person person = new person(); // 將Person 實體化
		model.addAttribute("person",person);
		return "form";
	}
	
	@PostMapping("add")
	public String add(@ModelAttribute person person,Model model) {
		model.addAttribute("person",person);
		return "add";
	}
	
	
	
	
}
