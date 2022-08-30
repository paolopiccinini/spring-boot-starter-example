package com.example.springbootstarterexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootstarterexample.domain.SomeEntity;

public interface SomeEntityRepository extends JpaRepository<SomeEntity, Long>{

}
