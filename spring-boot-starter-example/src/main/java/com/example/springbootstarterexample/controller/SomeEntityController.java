package com.example.springbootstarterexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootstarterexample.domain.SomeEntity;
import com.example.springbootstarterexample.service.SomeService;

@RestController
@RequestMapping("someEntities")
public class SomeEntityController {
	
	private SomeService service;
	
	public SomeEntityController(SomeService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/{id}")
	public SomeEntity find(@PathVariable("id") Long id) {
		return service.getSomeEntity(id);
	}
	
	@PostMapping
	public SomeEntity save(@RequestBody SomeEntity entity) {
		return service.saveSomeEntity(entity);
	}

}
