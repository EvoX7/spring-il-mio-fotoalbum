package org.fotoalbum.controller.api;

import org.fotoalbum.service.CommentService;
import org.fotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/comment")
@CrossOrigin("*")
public class CommentControllerApi {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PhotoService photoService;
	
	
}



	
	
