package com.demo.belajarspring;

import com.demo.belajarspring.entity.Kategori;
import com.demo.belajarspring.repo.KategoriRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class BelajarSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringApplication.class, args);
	}
}


//@Component
//class DataInitializer implements CommandLineRunner{
//
//	@Autowired
//	private KategoriRepo kategoriRepo;
//
//	@Override
//	public void run(String... args) throws Exception {
//		String[] names = {"kalung", "bingkai", "kemeja"};
//
//		for (int i=0; i < names.length; i++){
//			kategoriRepo.save(
//					Kategori.builder()
//						.name(names[i])
//							.active(true)
//							.build());
//		}
//	}
//}