package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	
}
