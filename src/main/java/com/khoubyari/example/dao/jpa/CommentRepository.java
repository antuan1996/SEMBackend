package com.khoubyari.example.dao.jpa;

import com.khoubyari.example.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
