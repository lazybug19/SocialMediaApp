package com.example.social.Comments;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.example.social.Posts.PostRepository;
import com.example.social.User.UserRepository;

class CommentCreator {
    private String name;
    private Integer userID;

    public CommentCreator(String name, Integer userID) {
        this.name = name;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public CommentCreator getCreator() {
        return creator;
    }

    public void setCreator(CommentCreator creator) {
        this.creator = creator;
    }
}

@Service
public class CommentService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    // CRUD operations
    // Create
    public int createComment(String commentBody, Integer userID, Integer postID) {
        try {
            userRepository.findById(userID).get();
            try {
                postRepository.findById(postID).get();
                CommentModel newComment = new CommentModel();
                newComment.setcommentBody(commentBody);
                newComment.setuserID(userID);
                newComment.setpostID(postID);
                commentRepository.save(newComment);
                return 0; // comment created
            } catch (NoSuchElementException e) {
                return 1; // no post found
            }
        } catch (NoSuchElementException e) {
            return 2; // no user found
        }
    }

    // Read
    public CommentOutput getCommentById(Integer commentID) {
        try {
            CommentModel comment = commentRepository.findById(commentID).get();
            CommentOutput output = new CommentOutput();
            output.setcommentBody(comment.getcommentBody());
            output.setcommentID(comment.getcommentID());
            CommentCreator creator = new CommentCreator(userRepository.findById(comment.getuserID()).get().getName(),
                    comment.getuserID());
            output.setCreator(creator);
            return output;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    // Update
    public CommentModel updateComment(String commentBody, Integer commentID) {
        try {
            CommentModel comment = commentRepository.findById(commentID).get();
            comment.setcommentBody(commentBody);
            return commentRepository.save(comment);
        } catch (NoSuchElementException e) {
            return null; // no comment found
        }
    }

    // Delete
    public CommentModel deleteComment(Integer commentID) {
        try {
            CommentModel comment = commentRepository.findById(commentID).get();
            commentRepository.delete(comment);
            return comment;
        } catch (NoSuchElementException e) {
            return null; // no comment found
        }
    }

}
