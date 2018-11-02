package com.demo.belajarspring.entity.onetomany.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class RequestPosts {

    @Size(max = 100, min = 3, message = "max 100 dan min 3")
    @NotBlank(message = "title masih kosong")
    private String postsTitle;

    @Size(max = 10000, min = 1, message = "max 10000 dan min 1")
    @NotBlank(message = "content masih kosong")
    private String postsContent;

}
