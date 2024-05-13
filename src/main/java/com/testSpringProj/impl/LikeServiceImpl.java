package com.testSpringProj.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testSpringProj.bean.Like;
import com.testSpringProj.bean.Post;
import com.testSpringProj.bean.User;
import com.testSpringProj.exceptions.ResourceNotFoundException;
import com.testSpringProj.payloads.LikeDto;
import com.testSpringProj.repository.LikeRepo;
import com.testSpringProj.repository.PostRepo;
import com.testSpringProj.repository.UserRepo;
import com.testSpringProj.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {
	
	@Autowired 
	LikeRepo likeRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public void createLike(Like like) {
		
		this.likeRepo.save(like);

	}

	@Override
	public void deleteLike(Integer likeId) {
		
		Like like = this.likeRepo.findById(likeId)
				.orElseThrow(() ->new ResourceNotFoundException("Like", "LikeId", likeId));
		
		this.likeRepo.delete(like);

	}

	@Override
	public Integer updateLike(int likeCount, Integer likeId) {
		
		System.out.println("inside update like : " + likeCount + " likeid : " + likeId);

		Like like = this.likeRepo.findById(likeId)
				.orElseThrow(() -> new ResourceNotFoundException("Like", "LikeId", likeId));
		
		like.setCounter(likeCount);
		
		this.likeRepo.save(like);
		
		return likeCount;
	}

}
