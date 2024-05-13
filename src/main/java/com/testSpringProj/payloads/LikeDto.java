package com.testSpringProj.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto {
	
	private int likeId;
	private int counter;
	private UserDto user;
//	private PostDto post;

}
