package com.testSpringProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testSpringProj.bean.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
