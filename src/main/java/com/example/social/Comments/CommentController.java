package com.example.social.Comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.*;

class CreateCommentInput {
    private String commentBody;
    private Integer userID;
    private Integer postID;

    public String getcommentBody() {
        return commentBody;
    }

    public void setcommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Integer getuserID() {
        return userID;
    }

    public void setuserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getpostID() {
        return postID;
    }

    public void setpostID(Integer postID) {
        this.postID = postID;
    }
}

class EditCommentInput {
    private String commentBody;
    private Integer commentID;

    public String getcommentBody() {
        return commentBody;
    }

    public void setcommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Integer getcommentID() {
        return commentID;
    }

    public void setcommentID(Integer commentID) {
        this.commentID = commentID;
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
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    // comment API endpoints

    @PostMapping()
    public ResponseEntity<?> createComment(@RequestBody CreateCommentInput comment) {
        int flag = commentService.createComment(comment.getcommentBody(), comment.getuserID(), comment.getpostID());
        if (flag == 1) {
            ErrorOutput error = new ErrorOutput();
            error.setError("Post does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } else if (flag == 2) {
            ErrorOutput error = new ErrorOutput();
            error.setError("User does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Comment created successfully");
    }

    @GetMapping()
    public ResponseEntity<?> getComment(@RequestParam Integer commentID) {
        if (commentService.getCommentById(commentID) == null) {
            ErrorOutput error = new ErrorOutput();
            error.setError("Comment does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(commentService.getCommentById(commentID));
    }

    @PatchMapping()
    public ResponseEntity<?> editComment(@RequestBody EditCommentInput comment) {
        if (commentService.updateComment(comment.getcommentBody(), comment.getcommentID()) == null) {
            ErrorOutput error = new ErrorOutput();
            error.setError("Comment does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Comment edited successfully");
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteComment(@RequestParam Integer commentID) {
        if (commentService.deleteComment(commentID) == null) {
            ErrorOutput error = new ErrorOutput();
            error.setError("Comment does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
