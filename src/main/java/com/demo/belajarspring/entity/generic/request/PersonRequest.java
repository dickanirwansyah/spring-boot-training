package com.demo.belajarspring.entity.generic.request;


import com.demo.belajarspring.entity.generic.entity.Status;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PersonRequest {

    @NotBlank(message = "firstname must not be null.")
    private String firstname;

    @NotBlank(message = "lastname must not be null.")
    private String lastname;

    @Email(message = "email not valid.")
    @NotBlank(message = "email must cannot be null.")
    private String email;

    private Status status = Status.BELUM;

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
