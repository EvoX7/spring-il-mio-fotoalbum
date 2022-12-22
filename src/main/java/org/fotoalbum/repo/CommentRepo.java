package org.fotoalbum.repo;

import org.fotoalbum.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
