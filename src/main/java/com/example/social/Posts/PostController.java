package com.example.social.Posts;

import com.example.social.User.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

class CreatePostInput {
    private String postBody;
    private Integer userID;

    public String getpostBody() {
        return postBody;
    }

    public void setpostBody(String postBody) {
        this.postBody = postBody;
    }

    public Integer getuserID() {
        return userID;
    }

    public void setuserID(Integer userID) {
        this.userID = userID;
    }
}

class EditPostInput {
    private String postBody;
    private Integer postID;

    public String getpostBody() {
        return postBody;
    }

    public void setpostBody(String postBody) {
        this.postBody = postBody;
    }

    public Integer getpostID() {
        return postID;
    }

    public void setpostID(Integer postID) {
        this.postID = postID;
    }
}

class ErrorOutput {
    @JsonProperty("Error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    // post API Endpoints

    @PostMapping() // PUT
    public ResponseEntity<?> createNew(@RequestBody CreatePostInput post) throws ParseException {
        if (postService.createPost(post.getpostBody(), post.getuserID()) == null) {
            ErrorOutput error = new ErrorOutput();
            error.setError("User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok("Post Created Successfully");
    }

    @GetMapping() // GET
    public ResponseEntity<?> getPost(@RequestParam Integer postID) {
        if (postService.getPostById(postID) == null) {
            ErrorOutput error = new ErrorOutput();
            error.setError("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(postService.getPostById(postID));
    }

    @PatchMapping() // PATCH
    public ResponseEntity<?> editPost(@RequestBody EditPostInput post) {
        if (postService.updatePost(post.getpostID(), post.getpostBody()) == null) {
            ErrorOutput error = new ErrorOutput();
            error.setError("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok("Post edited successfully");
    }

    @DeleteMapping() // DELETE
    public ResponseEntity<?> deletePost(@RequestParam Integer postID) {
        if (postService.deletePost(postID) == null) {
            ErrorOutput error = new ErrorOutput();
            error.setError("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok("Post deleted successfully");
    }
}
