package com.demo.belajarspring.entity.manytomany.controller;

import com.demo.belajarspring.entity.manytomany.entity.Skill;
import com.demo.belajarspring.entity.manytomany.repo.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping(value = "/api/skill")
public class SkillController {

    @Autowired
    private SkillRepo skillRepo;

    /** list skill **/
    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Skill>> listAll(){
        return ResponseEntity.ok(
                skillRepo.findAll());
    }

    /** distinct **/
    @RequestMapping(produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TreeSet<Skill>> listSkill(){

        TreeSet<Skill> skills = skillRepo.findAll()
                .stream().collect(Collectors
                        .toCollection(() ->
                                new TreeSet<>(Comparator.comparing
                                        (Skill::getName))));

       /** test loging **/
       skills.forEach(System.out::println);

       return new ResponseEntity<TreeSet<Skill>>(skills, HttpStatus.OK);
    }

}

