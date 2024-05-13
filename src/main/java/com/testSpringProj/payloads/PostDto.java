package com.testSpringProj.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.testSpringProj.bean.Category;
import com.testSpringProj.bean.Comment;
import com.testSpringProj.bean.Like;
import com.testSpringProj.bean.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	
	private String categoryId;

	private CategoryDto category;
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
	
	private LikeDto like;
	
}
