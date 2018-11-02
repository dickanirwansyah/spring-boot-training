package com.demo.belajarspring.entity.training.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class Users implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "email masih kosong.")
    @Email(message = "email tidak valid.")
    private String email;

    @Size(min = 2, max = 100, message = "minimal 2 karakter maximal 100 karakter.")
    @NotBlank(message = "nama masih kosong.")
    private String name;

    @NotBlank(message = "password masih kosong.")
    private String password;

    @NotBlank(message = "umur masih kosong.")
    @Pattern(regexp = "[\\d]{2}", message = "umur tidak valid.")
    private String age;

    @NotBlank(message = "nomor handphone masih kosong.")
    private String phoneNum;

    public Users(){}

    public Users(String email, String name, String password, String age, String phoneNum){
        this.email = email;
        this.name = name;
        this.password = password;
        this.age = age;
        this.phoneNum = phoneNum;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getAge(){
        return age;
    }

    public void setAge(String age){
        this.age = age;
    }

    public String getPhoneNum(){
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }
}
