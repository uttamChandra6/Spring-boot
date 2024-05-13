package com.testSpringProj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testSpringProj.bean.Category;
import com.testSpringProj.bean.Post;
import com.testSpringProj.bean.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);

}
