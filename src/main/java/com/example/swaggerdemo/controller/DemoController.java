package com.example.swaggerdemo.controller;

import com.example.swaggerdemo.domain.Person;
import com.example.swaggerdemo.service.BusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BusinessService businessService;

    @Value("${spring.application.name}")
    private String name;

    @GetMapping("/demo")
    private String index() {
        System.out.println(name);
        logger.info(String.format("calling method %s ", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return "Hello World!";
    }

    @PostMapping("/person")
    private int savePerson(@RequestBody Person person) {

        logger.info(String.format("calling method %s with person %s ", Thread.currentThread().getStackTrace()[1].getMethodName(), person.toString()));
        Person p = businessService.savePerson(person);
        return p.getId();

    }

    @GetMapping("/persons")
    private List<Person> getAllPerson() {

        logger.info(String.format("calling method %s ", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return businessService.findAllPersons();

    }

    @Operation(summary = "Get a person by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Person",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not found",
                    content = @Content) })
    @GetMapping(path = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Person> getPersonById(@PathVariable("id") int id) {
        logger.info(String.format("calling method %s with id %d ", Thread.currentThread().getStackTrace()[1].getMethodName(), id));
        Optional<Person> person = businessService.getPersonById(id);
        return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
    }

    @DeleteMapping("/person/{id}")
    private int deletePerson(@PathVariable("id") int id) {
        logger.info(String.format("calling method %s with id %d ", Thread.currentThread().getStackTrace()[1].getMethodName(), id));
        return businessService.deletePersonById(id);
    }

    @PutMapping(path = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private Person updatePerson(@PathVariable("id") int id, @RequestBody Person inPerson) {
        logger.info(String.format("calling method %s with id %d and person %s", Thread.currentThread().getStackTrace()[1].getMethodName(), id, inPerson.toString()));

        Optional<Person> person = businessService.getPersonById(id);
        person.get().setAge(inPerson.getAge());
        person.get().setEmail(inPerson.getEmail());
        person.get().setName(inPerson.getName());


        Person p =  businessService.savePerson(person.get());
        return p;

    }
}
