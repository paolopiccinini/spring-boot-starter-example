package com.example.springbootconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootconsumer.domain.SomeEntityConsumer;

public interface SomeEntityConsumerRepository extends JpaRepository<SomeEntityConsumer, Long> {

}
