package com.humuson.push2u.user.dao;

import java.util.Map;

import com.humuson.push2u.user.domain.User;

/**
 * 회원 관련 처리 규약 선언
 * @author 황집중
 *
 */

public interface UserDao {
	
	// 회원 로그인
	public User login(Map<String, String> userMap) throws RuntimeException;
	
	// 아이디로 회원 찾기
	public int findUserById(String userId) throws RuntimeException;
	
	// 인증문자 전송
	public void authPhoneNum(Map<String, String> authMap) throws RuntimeException;
	
	// 회원가입
	public void join(Map<String, String> joinMap) throws RuntimeException;
	
	// App User insert
	public void insertAppUser(Map<String, String> appUserMap) throws RuntimeException;
}
