package com.example.social.Posts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Integer> {
    @SuppressWarnings("null")
    Optional<PostModel> findById(Integer postID);

    List<PostModel> findAllByOrderByDateDesc();
}
