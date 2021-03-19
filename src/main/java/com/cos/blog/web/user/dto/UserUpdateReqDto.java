package com.cos.blog.web.user.dto;

import lombok.Data;

@Data
public class UserUpdateReqDto {
	private String username;
	private String password;
	private String email;
	
	//toEntity안만드는 이유는 더티 체킹 할 거이기 때문
}
