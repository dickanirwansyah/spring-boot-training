package com.demo.belajarspring.controller;

import com.demo.belajarspring.entity.Produk;
import com.demo.belajarspring.exception.ResourceNotFoundException;
import com.demo.belajarspring.repo.KategoriRepo;
import com.demo.belajarspring.repo.ProdukRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ProdukController {

    @Autowired
    private ProdukRepo produkRepo;

    @Autowired
    private KategoriRepo kategoriRepo;

    @GetMapping(value = "/produk")
    public ResponseEntity<List<Produk>> listProduk(){
        List<Produk> produks = new ArrayList<>();

        for (Produk produk : produkRepo.findAll()){
            produks.add(produk);
        }

        return ResponseEntity
                .ok(produks);
    }

    @PostMapping(value = "/{kategoriId}/produk")
    public ResponseEntity<Produk> newProduk(@PathVariable("kategoriId") Long kategoriId,
                                            @RequestBody Produk produk){

        return kategoriRepo.findById(kategoriId)
                .map(kategori -> {
                    produk.setKategori(kategori);
                    return ResponseEntity.ok(produkRepo.save(produk));
                }).orElseThrow(() ->
                        new ResourceNotFoundException("kategori id tidak ada"));
    }

    @PutMapping(value = "/{kategoriId}/produk/{produkId}")
    public ResponseEntity<Produk> updateProduk(@PathVariable("kategoriId") Long kategoriId,
                                               @PathVariable("produkId") Long produkId,
                                               @RequestBody Produk produk){

        return kategoriRepo.findById(kategoriId)
                .map(kategori -> {
                    return produkRepo.findById(produkId)
                            .map(currentProduk -> {
                                currentProduk.setKategori(kategori);
                                currentProduk.setName(produk.getName());
                                currentProduk.setPrice(produk.getPrice());
                                currentProduk.setStock(produk.getStock());
                                return ResponseEntity
                                        .ok(produkRepo.save(currentProduk));
                            })
                            .orElseThrow(() -> new ResourceNotFoundException("produk id tidak ada"));
                })
                .orElseThrow(() -> new ResourceNotFoundException("kategori id tidak ada"));
    }

    @DeleteMapping(value = "/produk/{produkId}")
    public ResponseEntity<?> delete(@PathVariable(value = "produkId") Long produkId){

        return ResponseEntity.ok(
                produkRepo.findById(produkId)
                .map(produk -> {
                    produkRepo.delete(produk);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() ->
                        new ResourceNotFoundException("productid tidak ada.")));
    }
}
