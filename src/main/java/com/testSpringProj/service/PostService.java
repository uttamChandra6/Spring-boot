package com.testSpringProj.service;

import java.util.List;

import com.testSpringProj.payloads.PostDto;
import com.testSpringProj.payloads.PostResponse;

public interface PostService {
	
	PostDto getPost(Integer postId);
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy);
	List<PostDto> getPostByUser(Integer userId);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> searchPosts(String keyword);
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer postId);
	void deletePost(Integer postId);

}
