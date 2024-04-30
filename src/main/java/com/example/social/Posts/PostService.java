package com.example.social.Posts;

import java.text.ParseException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.example.social.User.UserRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.social.Comments.CommentRepository;
import com.example.social.Comments.CommentModel;

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

// Post details business logic

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    // CRUD operations
    // Create
    public PostModel createPost(String postBody, Integer userID) throws ParseException {
        try {
            userRepository.findById(userID).get();
            PostModel newPost = new PostModel();
            newPost.setpostBody(postBody);
            newPost.setuserID(userID);
            newPost.setDate(new Date());

            postRepository.save(newPost);
            return newPost;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    // Read
    public PostOutput getPostById(Integer postID) {
        try {
            PostModel Post = postRepository.findById(postID).get();
            PostOutput postOutput = new PostOutput();
            postOutput.setpostBody(Post.getpostBody());
            postOutput.setpostID(Post.getpostID());
            postOutput.setDate(Post.getDate());
            List<CommentOutput> comments = new ArrayList<>();
            List<CommentModel> commentList = commentRepository.findAllByPostID(postID).get();
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
            return postOutput;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    // Update
    public PostModel updatePost(Integer postID, String PostDetails) {
        try {
            PostModel existingPost = postRepository.findById(postID).get();
            existingPost.setpostBody(PostDetails);
            return postRepository.save(existingPost);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    // Delete
    public PostModel deletePost(Integer postID) {
        try {
            PostModel Post = postRepository.findById(postID).get();
            postRepository.delete(Post);
            return Post;
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
