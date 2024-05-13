package com.testSpringProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testSpringProj.bean.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
