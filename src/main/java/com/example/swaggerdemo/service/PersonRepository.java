package com.example.swaggerdemo.service;


import com.example.swaggerdemo.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
