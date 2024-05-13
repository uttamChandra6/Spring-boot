package com.testSpringProj.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.testSpringProj.service.serviceImpl;

@RestController
public class myResource {
	
	@Autowired
	serviceImpl service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		System.out.println("I am Spring Boot Project");
		return "I am Spring Boot Project";
	}
	
	@RequestMapping(value = "storeNameInDB/{name}", method = RequestMethod.GET)
	public String storeNameInDB(@PathVariable("name") String name) {
		service.storeDataInDBImpl(name);
		return name;
	}

}
