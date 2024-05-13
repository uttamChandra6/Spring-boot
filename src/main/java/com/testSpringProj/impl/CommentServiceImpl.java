package com.testSpringProj.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testSpringProj.bean.Comment;
import com.testSpringProj.bean.Post;
import com.testSpringProj.bean.User;
import com.testSpringProj.exceptions.ResourceNotFoundException;
import com.testSpringProj.payloads.CommentDto;
import com.testSpringProj.repository.CommentRepo;
import com.testSpringProj.repository.PostRepo;
import com.testSpringProj.repository.UserRepo;
import com.testSpringProj.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired 
	CommentRepo commentRepo;
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", " userId ", userId));
		
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", " postId ", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		
		Comment savedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("Comment", " commentId ", commentId));
		
		this.commentRepo.delete(comment);

	}

}
