package com.humuson.push2u.user.service;

import com.humuson.push2u.user.domain.User;

/**
 * 회원 관련 비즈니스 규약(추상메소드) 선언
 * @author 황집중
 *
 */

public interface UserService {
	
	// 회원 로그인
	public User login(String userId, String userPasswd) throws RuntimeException;
	
	// 아이디 중복검사
	public int idCheck(String userId) throws RuntimeException;  
	
	// 인증번호 전송
	public void authPhoneNum(String phoneNum, String authNum) throws RuntimeException;
	
	// 회원가입
	public void join(String userId, String userPasswd, String userName, String phoneNum) throws RuntimeException;
	
	// App User insert
	public void insertAppUser(String custId, String phoneNum, String device, String osVer, String appVer) throws RuntimeException;
	
}
