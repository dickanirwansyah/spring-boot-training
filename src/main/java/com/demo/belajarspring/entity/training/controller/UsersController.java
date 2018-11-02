package com.demo.belajarspring.entity.training.controller;

import com.demo.belajarspring.entity.training.entity.Users;
import com.demo.belajarspring.entity.training.repo.UsersRepo;
import com.demo.belajarspring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private UsersRepo usersRepo;

    Map<String, String> errors;

    @GetMapping
    public ResponseEntity<List<Users>> listUsers(){
        return ResponseEntity.ok(usersRepo.findAll());
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<Object> createUsers(@RequestBody @Valid Users users,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(errors, HttpStatus.NOT_ACCEPTABLE);
        }

        Users usersEmail = usersRepo.findByEmail(users.getEmail());

        if (usersEmail!=null){
            return new ResponseEntity<Object>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(
                usersRepo.save(users)
        );
    }

    @GetMapping(value = "/{usersId}")
    public ResponseEntity<Users> getId(@PathVariable("usersId") Long usersId){
        return usersRepo.findById(usersId)
                .map(currentUsers -> {
                    return ResponseEntity.ok(currentUsers);
                }).orElseThrow(() ->
                        new ResourceNotFoundException("data user tidak ditemukan."));
    }


    @PutMapping(value = "/updateUser/{userId}")
    public ResponseEntity<Object> updateUsers(@PathVariable("userId") Long userId,
                                              @RequestBody @Valid Users users,
                                              BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            errors = new HashMap<>();
            for (FieldError fieldError:bindingResult.getFieldErrors()){
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(errors, HttpStatus.NOT_ACCEPTABLE);
        }


        Users usersEmail = usersRepo.findByEmail(users.getEmail());
        if (usersEmail!=null){
            return new ResponseEntity<Object>(HttpStatus.CONFLICT);
        }

        return usersRepo.findById(userId)
                .map(currentData -> {
                    currentData.setAge(users.getAge());
                    currentData.setEmail(users.getEmail());
                    currentData.setName(users.getName());
                    currentData.setPassword(users.getPassword());
                    currentData.setPhoneNum(users.getPhoneNum());
                    usersRepo.save(currentData);
                    return new ResponseEntity<Object>(currentData, HttpStatus.OK);
                }).orElseThrow(() ->
                        new ResourceNotFoundException("iduser tidak ada"));
    }
}
