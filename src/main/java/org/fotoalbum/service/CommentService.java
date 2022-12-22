package org.fotoalbum.service;

import org.fotoalbum.pojo.Comment;
import org.fotoalbum.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	private CommentRepo commentRepo;

	public Comment save(Comment comment) {
		return commentRepo.save(comment);
	}
}
