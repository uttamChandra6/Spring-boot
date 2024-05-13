package com.testSpringProj.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.testSpringProj.bean.userDetails;
import com.testSpringProj.repository.DBARepository;

@Service
public class serviceImpl {
	
	@Autowired
	DBARepository repository;
	
	public void storeDataInDBImpl(String name) {
		repository.saveAll(Arrays.asList(new userDetails(name)));
		return;
	}

}
