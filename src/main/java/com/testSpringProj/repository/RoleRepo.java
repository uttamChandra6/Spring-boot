package com.testSpringProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testSpringProj.bean.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
