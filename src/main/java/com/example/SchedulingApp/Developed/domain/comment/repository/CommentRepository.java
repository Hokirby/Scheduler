package com.example.SchedulingApp.Developed.domain.comment.repository;

import com.example.SchedulingApp.Developed.domain.comment.entity.Comment;
import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorMessageCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findCommentById(Long id);

    default Comment findCommentByIdOrElseThrow(Long id) {
        return findCommentById(id)
                .orElseThrow(() -> new ApplicationException(ErrorMessageCode.NOT_FOUND, "Comment Not Found"));
    }
}
