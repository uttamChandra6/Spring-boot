package com.testSpringProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testSpringProj.bean.Like;

public interface LikeRepo extends JpaRepository<Like, Integer> {

}
