package com.example.social.Comments;

import jakarta.persistence.*;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "comments")
public class CommentModel {

    // ID will be generated using database identity column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentID", unique = true)
    private Integer commentID;

    @Column(name = "userID")
    private Integer userID;

    @Column(name = "postID")
    private Integer postID;

    @Column(name = "commentBody")
    private String commentBody;

    public Integer getcommentID() {
        return commentID;
    }

    public void setpostID(Integer postID) {
        this.postID = postID;
    }

    public Integer getpostID() {
        return postID;
    }

    public void setuserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getuserID() {
        return userID;
    }

    public void setcommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public String getcommentBody() {
        return commentBody;
    }
}
