package com.humuson.push2u.user.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humuson.push2u.user.controller.UserController;
import com.humuson.push2u.user.dao.UserDao;
import com.humuson.push2u.user.domain.User;

@Service("userService")
public class UserServiceImple implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDao userDao;
	
	public UserServiceImple(){}
	
	public UserDao getUserDao(){
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	// 로그인
	@Override
	public User login(String userId, String userPasswd) throws RuntimeException {
		
		Map<String, String> userMap = new HashMap<String, String>();
		
		userMap.put("user_id", userId);
		userMap.put("passwd", userPasswd);
		
		return userDao.login(userMap);
	}
	
	// 아이디 중복검사
	@Override
	public int idCheck(String userId) throws RuntimeException {
		
		return userDao.findUserById(userId);
	}
	
	// 인증문자 전송
	@Override
	public void authPhoneNum(String phoneNum, String authNum) throws RuntimeException {

		Map<String, String> authMap = new HashMap<String, String>();
		
		authMap.put("phoneNum", phoneNum);
		authMap.put("authNum", "[PUSH2U] 인증번호는 " + authNum + "입니다.");
		
		userDao.authPhoneNum(authMap);
	}
	
	// 회원가입
	@Override
	public void join(String userId, String userPasswd, String userName, String phoneNum) throws RuntimeException {
		
		Map<String, String> joinMap = new HashMap<String, String>();
		
		joinMap.put("userId", userId);
		joinMap.put("userPasswd", userPasswd);
		joinMap.put("userName", userName);
		joinMap.put("phoneNum", phoneNum);
		
		userDao.join(joinMap);
		
	}
	
	// custId 체크
	@Override
	public int checkCust(String custId) throws RuntimeException {
		return userDao.checkCust(custId);
	}

	// App User update
	@Override
	public void updateAppUser(String custId, String phoneNum, String device, String osVer, String appVer) throws RuntimeException {
		
		Map<String, String> appUserMap = new HashMap<String, String>();
		
		appUserMap.put("custId", custId);
		appUserMap.put("phoneNum", phoneNum);
		appUserMap.put("device", device);
		appUserMap.put("osVer", osVer);
		appUserMap.put("appVer", appVer);
		
		userDao.updateAppUser(appUserMap);
		
	}
	
	// App User insert
	@Override
	public void insertAppUser(String custId, String phoneNum, String device, String osVer, String appVer) throws RuntimeException {

		Map<String, String> appUserMap = new HashMap<String, String>();
		
		appUserMap.put("custId", custId);
		appUserMap.put("phoneNum", phoneNum);
		appUserMap.put("device", device);
		appUserMap.put("osVer", osVer);
		appUserMap.put("appVer", appVer);
		
		userDao.insertAppUser(appUserMap);
		
		
	}

}
