package com.example.social;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import com.example.social.User.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.social.Comments.CommentRepository;
import com.example.social.Posts.*;
import com.example.social.Comments.*;

class CommentCreator {
    private String name;
    private Integer userID;

    public CommentCreator(String name, Integer userID) {
        this.name = name;
        this.userID = userID;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Integer getuserID() {
        return userID;
    }

    public void setuserID(Integer userID) {
        this.userID = userID;
    }
}

class CommentOutput {
    private String commentBody;
    private Integer commentID;
    private CommentCreator creator;

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

    public CommentCreator getcreator() {
        return creator;
    }

    public void setcreator(CommentCreator creator) {
        this.creator = creator;
    }
}

class PostOutput {
    private String postBody;
    private Integer postID;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private List<CommentOutput> comments;

    public List<CommentOutput> getcomments() {
        return comments;
    }

    public void setcomments(List<CommentOutput> comments) {
        this.comments = comments;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

@CrossOrigin
@RestController
@RequestMapping("/")
public class MainPage {
    @Autowired
    PostRepository postRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping() // GET
    public ResponseEntity<List<PostOutput>> getAllPosts() {
        List<PostModel> allPosts = postRepo.findAllByOrderByDateDesc();
        List<PostOutput> allPostsOutput = new ArrayList<>();
        for (PostModel post : allPosts) {
            PostOutput postOutput = new PostOutput();
            postOutput.setpostBody(post.getpostBody());
            postOutput.setpostID(post.getpostID());
            postOutput.setDate(post.getDate());
            List<CommentOutput> comments = new ArrayList<>();
            List<CommentModel> commentList = commentRepository.findAllByPostID(post.getpostID()).get();
            for (CommentModel comment : commentList) {
                CommentOutput commentOutput = new CommentOutput();
                commentOutput.setcommentBody(comment.getcommentBody());
                commentOutput.setcommentID(comment.getcommentID());
                commentOutput
                        .setcreator(new CommentCreator(userRepository.findById(comment.getuserID()).get().getName(),
                                comment.getuserID()));
                comments.add(commentOutput);
            }
            postOutput.setcomments(comments);
            allPostsOutput.add(postOutput);
        }
        return ResponseEntity.ok(allPostsOutput);
    }
}