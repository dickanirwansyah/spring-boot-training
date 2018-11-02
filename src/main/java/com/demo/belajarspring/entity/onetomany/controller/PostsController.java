package com.demo.belajarspring.entity.onetomany.controller;

import com.demo.belajarspring.entity.onetomany.entity.Posts;
import com.demo.belajarspring.entity.onetomany.repository.CommentRepo;
import com.demo.belajarspring.entity.onetomany.repository.PostsRepo;
import com.demo.belajarspring.entity.onetomany.request.RequestPosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping(value = "/api/posts")
public class PostsController {

    @Autowired
    private PostsRepo postsRepo;

    Map<String, String> validate;

    @RequestMapping(method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Posts>> listPost(){
        List<Posts> posts = postsRepo.findByCreatedAt();
        if (posts.isEmpty()){
            return new ResponseEntity<List<Posts>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Posts>>(posts, HttpStatus.OK);
    }

    @RequestMapping(value = "/desc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Posts>> listPostDesc(){
        List<Posts> posts = postsRepo.findByCreatedAtDesc();
        if (posts.isEmpty()){
            return new ResponseEntity<List<Posts>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Posts>>(posts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> newPosts(@RequestBody @Valid RequestPosts rp,
                                                 BindingResult result){
        /** check validation **/
        if (result.hasErrors()){
            validate = new HashMap<>();
            for (FieldError fieldError: result.getFieldErrors()){
                validate.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(validate, HttpStatus.NOT_ACCEPTABLE);
        }

        /** if true **/

        return ResponseEntity.ok(
                postsRepo.save(
                        Posts.builder()
                        .title(rp.getPostsTitle())
                        .content(rp.getPostsContent())
                        .createdAt(new Date())
                        .build()
                )
        );
    }
}
