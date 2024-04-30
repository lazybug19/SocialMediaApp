package com.example.social.Comments;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Integer> {
    @SuppressWarnings("null")
    Optional<CommentModel> findById(Integer CommentID);

    Optional<List<CommentModel>> findAllByPostID(Integer PostID);
}
