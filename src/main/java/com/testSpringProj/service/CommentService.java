package com.testSpringProj.service;

import com.testSpringProj.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);
	void deleteComment(Integer commentId);
	
}
