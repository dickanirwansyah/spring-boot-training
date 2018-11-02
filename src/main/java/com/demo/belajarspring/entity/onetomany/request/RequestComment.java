package com.demo.belajarspring.entity.onetomany.request;

import com.demo.belajarspring.entity.onetomany.entity.Posts;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class RequestComment {

    @NotBlank(message = "comment name masih kosong.")
    private String commentName;

    @NotBlank(message = "comment masih kosong")
    private String commentComments;

    private Posts posts;
}
