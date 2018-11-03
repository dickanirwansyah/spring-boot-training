package com.demo.belajarspring.entity.generic.controller;

import com.demo.belajarspring.entity.generic.entity.Person;
import com.demo.belajarspring.entity.generic.entity.Status;
import com.demo.belajarspring.entity.generic.request.PersonRequest;
import com.demo.belajarspring.entity.generic.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    @Autowired
    private IPersonService iPersonService;

    Map<String, String> errorValidation;

    @GetMapping
    public ResponseEntity<List<Person>> listPerson(){
        return ResponseEntity.ok(
                iPersonService.list()
        );
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createPerson(@RequestBody @Valid PersonRequest request,
                                               BindingResult bindingResult){

        /** check data kosong**/
        if (bindingResult.hasErrors()){
            errorValidation = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()){
                errorValidation.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Object>(errorValidation, HttpStatus.NOT_ACCEPTABLE);
        }

        Person person = Person.builder()
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .status(Status.BELUM)
                .build();

        /** check duplikat data **/
        boolean valid = iPersonService.inserted(person);
        if (valid == false){
            return new ResponseEntity<Object>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<Object>(valid, HttpStatus.CREATED);
    }
}
