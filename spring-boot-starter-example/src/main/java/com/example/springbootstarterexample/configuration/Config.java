package com.example.springbootstarterexample.configuration;

import org.hibernate.annotations.Immutable;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.springbootstarterexample.controller.SomeEntityController;
import com.example.springbootstarterexample.domain.SomeEntity;
import com.example.springbootstarterexample.service.SomeServiceImpl;

@Configuration
@AutoConfigureAfter(JpaRepositoriesAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "com.example.springbootstarterexample.repository")
@Import({SomeServiceImpl.class, SomeEntityController.class})
public class Config {

}
