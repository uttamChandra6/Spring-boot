package com.testSpringProj.service;

import com.testSpringProj.bean.Like;
import com.testSpringProj.payloads.LikeDto;

public interface LikeService {

	void createLike(Like like);
	Integer updateLike(int likeCount, Integer likeId);
	void deleteLike(Integer likeId);
	
}
