package com.testSpringProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testSpringProj.bean.userDetails;

public interface DBARepository extends JpaRepository<userDetails, Integer> {

}
