package com.cos.blog.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;
import com.cos.blog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User 회원수정(int id,UserUpdateReqDto userUpdateReqDto) {
		User userEntity = userRepository.findById(id).get();	//영속화 - 1차 캐시
		String password = bCryptPasswordEncoder.encode(userUpdateReqDto.getPassword());//ECrypt해시 암호화
		
		userEntity.setPassword(password);
		userEntity.setEmail(userUpdateReqDto.getEmail());
		//사라지기 직전에 일어나는 일 
		//영속화를 시키면 1차 캐시에(테이블?메모리공간)들어가게된다.
		//2차캐시(새로운 공간) 이유? = > 비교하기 위해
		
		return userEntity;
	}// 더티 체킹(변하면 데이터베이스에 커밋 때려버림)flush
}
