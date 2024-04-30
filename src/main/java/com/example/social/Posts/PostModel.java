package com.example.social.Posts;

import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "posts")
public class PostModel {

    // ID will be generated using database identity column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postID", unique = true)
    private Integer postID;

    @Column(name = "userID")
    private Integer userID;

    @Column(name = "postBody")
    private String postBody;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    public Integer getpostID() {
        return postID;
    }

    public void setuserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getuserID() {
        return userID;
    }

    public void setpostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getpostBody() {
        return postBody;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

}
