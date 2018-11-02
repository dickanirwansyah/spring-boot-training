package com.demo.belajarspring.entity.onetomany.controller;

import com.demo.belajarspring.entity.onetomany.entity.Comment;
import com.demo.belajarspring.entity.onetomany.entity.Posts;
import com.demo.belajarspring.entity.onetomany.repository.CommentRepo;
import com.demo.belajarspring.entity.onetomany.repository.PostsRepo;
import com.demo.belajarspring.entity.onetomany.request.RequestComment;
import com.demo.belajarspring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostsRepo postsRepo;

    Map<String, String> validates;

    /** comment by post **/
    @RequestMapping(value = "/posts/{postsId}")
    public ResponseEntity<List<Comment>> listComment(@PathVariable(value = "postsId") Long postsId){
        Posts posts = postsRepo.findByIdIn(postsId);
        if (posts==null){
            return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
        }

        List<Comment> comments = commentRepo.findByPosts(posts);
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }


    /** create comment **/
    @RequestMapping(value = "/{postsId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createComment(@PathVariable("postsId") Long postsId,
                                                @RequestBody @Valid RequestComment rm,
                                                BindingResult result){
        if (result.hasErrors()){
            validates = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()){
                validates.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(validates, HttpStatus.NOT_ACCEPTABLE);
        }

        return postsRepo.findById(postsId)
                .map(posts -> {

                    Comment comment = Comment.builder()
                            .name(rm.getCommentName())
                            .comments(rm.getCommentComments())
                            .posts(posts)
                            .build();
                    commentRepo.save(comment);
                    return new ResponseEntity(comment, HttpStatus.CREATED);

                }).orElseThrow(() ->
                        new ResourceNotFoundException("id : "+postsId+" tidak ada"));
    }

}
