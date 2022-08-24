package com.example.springbootstarterexample.service;

import org.springframework.stereotype.Service;

import com.example.springbootstarterexample.domain.SomeEntity;
import com.example.springbootstarterexample.exception.SomeEntityNotFoundException;
import com.example.springbootstarterexample.repository.SomeEntityRepository;

@Service
public class SomeServiceImpl implements SomeService {
	
	private SomeEntityRepository repository;
	
	public SomeServiceImpl(SomeEntityRepository repository) {
		this.repository = repository;
	}

	@Override
	public SomeEntity getSomeEntity(Long id) {
		return repository.findById(id).orElseThrow(() -> new SomeEntityNotFoundException("entity not found with id:" + id));

	}

	@Override
	public SomeEntity saveSomeEntity(SomeEntity entity) {
		return repository.save(entity);
	}

}
