package com.demo.belajarspring.controller;

import com.demo.belajarspring.entity.Kategori;
import com.demo.belajarspring.repo.KategoriRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class KategoriController {

    @Autowired
    private KategoriRepo kategoriRepo;

    @GetMapping(value = "/kategori")
    public ResponseEntity<List<Kategori>> list(){
        List<Kategori> kategoris = new ArrayList<>();

        for (Kategori kategori : kategoriRepo.findAll()){
            kategoris.add(kategori);
        }

        return ResponseEntity.ok(
               kategoris
        );
    }
}
