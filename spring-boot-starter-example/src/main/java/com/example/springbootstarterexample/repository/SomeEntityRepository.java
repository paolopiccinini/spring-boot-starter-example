package com.example.springbootstarterexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootstarterexample.domain.SomeEntity;

@Repository
public interface SomeEntityRepository extends JpaRepository<SomeEntity, Long>{

}
