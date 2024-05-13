package com.testSpringProj.main;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.vote.ConsensusBased;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testSpringProj.payloads.LikeDto;
import com.testSpringProj.service.LikeService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("api/likes")
public class LikeController {
	
	@Autowired
	LikeService likeService;
	
	@PutMapping("/{likeId}")
	public Integer updateLike(@PathVariable String likeId) {
		
		
		
		return Integer.valueOf(1);
	}

}
