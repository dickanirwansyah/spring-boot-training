package com.demo.belajarspring.entity.onetomany.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "posts")
public class Posts implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title must not be null")
    private String title;

    @NotBlank(message = "content must not be null")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at")
    private Date createdAt;

}
