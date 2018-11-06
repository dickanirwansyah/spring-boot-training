package com.demo.belajarspring.entity.manytomany.controller;

import com.demo.belajarspring.entity.manytomany.entity.Employee;
import com.demo.belajarspring.entity.manytomany.entity.Skill;
import com.demo.belajarspring.entity.manytomany.repo.EmployeeRepo;
import com.demo.belajarspring.entity.manytomany.repo.SkillRepo;
import com.demo.belajarspring.entity.manytomany.request.RequestEmployee;
import com.demo.belajarspring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private SkillRepo skillRepo;

    Map<String, String> errorValidation;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> newEmployee(@RequestBody @Valid RequestEmployee request,
                                                BindingResult result){
        if (result.hasErrors()){
            errorValidation = new HashMap<>();
            for (FieldError fieldError: result.getFieldErrors()){
                errorValidation.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(errorValidation, HttpStatus.NOT_ACCEPTABLE);
        }

        Employee employee = Employee
                .builder().code(request.getCode())
                .email(request.getEmail())
                .name(request.getName())
                .phone(request.getPhone())
                .hireDate(request.getHireDate())
                .build();



        employeeRepo.save(employee);
        return new ResponseEntity<Object>(employee, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{employeeId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getId(@PathVariable("employeeId") Long employeeId){
        return employeeRepo.findById(employeeId)
                .map(employee -> {
                    return ResponseEntity.ok(employee);
                }).orElseThrow(() ->
                        new ResourceNotFoundException("employeeId {} : "+employeeId+
                        "not found."));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> listAll(){
        return ResponseEntity.ok(employeeRepo.findAll()
                .stream()
                .sorted(Comparator
                        .comparing(Employee::getHireDate))
                .collect(Collectors.toList()));
    }

    @PostMapping(value = "/employee")
    public ResponseEntity<Employee> newEmployeeSkill(@RequestParam("name") String name,
                                                   @RequestParam("email") String email,
                                                   @RequestParam("phone") String phone,
                                                   @RequestParam("code") String code,
                                                   @RequestParam("skillIds[]") Long[] skillIds){


        Set<Skill> skills = new HashSet<>();
        for (Long skillId : skillIds){
            skills.add(skillRepo.findByIdIs(skillId));
        }

        Employee employee  = Employee
                .builder()
                .code(code)
                .name(name)
                .email(email)
                .phone(phone)
                .hireDate(new Date())
                .skills(skills)
                .build();

        employeeRepo.save(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }
}
