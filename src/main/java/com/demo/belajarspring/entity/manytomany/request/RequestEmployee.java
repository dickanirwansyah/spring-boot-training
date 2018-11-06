package com.demo.belajarspring.entity.manytomany.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class RequestEmployee {

    @NotBlank(message = "code must not be null.")
    private String code;

    @NotBlank(message = "name must not be null.")
    private String name;

    @NotBlank(message = "email must not be null.")
    @Email(message = "email not valid.")
    private String email;

    @NotBlank(message = "phone must not be null.")
    private String phone;

    @NotNull(message = "hire date must not be null.")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    public Date getHireDate(){
        return hireDate;
    }

    public void setHireDate(Date hireDate){
        this.hireDate = hireDate;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
}
