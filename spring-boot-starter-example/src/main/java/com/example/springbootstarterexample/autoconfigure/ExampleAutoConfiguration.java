package com.example.springbootstarterexample.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.springbootstarterexample.controller.SomeEntityController;
import com.example.springbootstarterexample.service.SomeServiceImpl;

@AutoConfiguration(after = JpaRepositoriesAutoConfiguration.class)
//@AutoConfigureAfter(JpaRepositoriesAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "com.example.springbootstarterexample.repository")
@Import({SomeServiceImpl.class, SomeEntityController.class /*, SomeEntity.class NOT WORKING*/})
@EntityScan(basePackages = "com.example.springbootstarterexample.domain")
public class ExampleAutoConfiguration {

}
