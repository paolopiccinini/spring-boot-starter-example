package com.example.springbootstarterexample.service;

import com.example.springbootstarterexample.domain.SomeEntity;

public interface SomeService {
	
	SomeEntity getSomeEntity(Long id);
	SomeEntity saveSomeEntity(SomeEntity entity);

}
