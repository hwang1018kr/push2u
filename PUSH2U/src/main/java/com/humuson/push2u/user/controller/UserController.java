package com.humuson.push2u.user.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.humuson.push2u.util.Encoder;
import com.humuson.push2u.push.service.PushService;
import com.humuson.push2u.user.domain.User;
import com.humuson.push2u.user.service.UserService;

/**
 * ȸ�� ���� ��û(�α���, ���� ��) ó�� ��Ʈ�ѷ�
 * @author Ȳ����
 *
 */

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/*
	 * �α���
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void login(String userId, String userPasswd, HttpSession session, HttpServletResponse response) throws IOException {
		
		User loginUser = null;
		
		String encodePw = Encoder.encoding(userPasswd, "SHA-512");
		
		logger.debug("######################  EncodePW : " + encodePw);
		
		loginUser = userService.login(userId, encodePw);
		
		if(loginUser != null) {
			
			session.setAttribute("userLoginInfo", loginUser);
			session.setAttribute("userId", loginUser.getUserId());
			session.setAttribute("phoneNum", loginUser.getPhoneNum());
			
			response.sendRedirect("/push/home");
			
		} else {
			
			response.sendRedirect("/?code=loginFail");
		}
		
	}
	
	/*
	 * ���̵� �ߺ��˻�
	 */
	@RequestMapping(value="/idCheck", method=RequestMethod.POST)
	public @ResponseBody String idCheck(String userId) {
		
		int check = 0;
		
		String NON_EXIST = "NON_EXIST";
		String EXIST 	 = "EXIST";
		
		check = userService.idCheck(userId);
		
		if(check == 0) {
			return NON_EXIST;
		} else {
			return EXIST;
		} 
	}
	
	/*
	 * ������ȣ ���� & �������� ����
	 */
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public @ResponseBody String makeAuthNum(String phoneNum) {
		
		String authNum = "";
		
		if(phoneNum != null) {
			
			int certNum = (int) (Math.floor(Math.random() * 10000)+1000);
			
			if(certNum > 10000){
				certNum = certNum - 1000;
			}
			
			authNum = String.valueOf(certNum);
		}
		
		userService.authPhoneNum(phoneNum, authNum);
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@    [������ȣ] : " + authNum);
		
		return authNum;
		
	}
	
	/*
	 * ȸ������
	 */
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public void join(String userId, String userPasswd, String userName, String phoneNum, HttpServletResponse response) {
		
		String encodedPw = Encoder.encoding(userPasswd, "SHA-512");
		
		userService.join(userId, encodedPw, userName, phoneNum);
		
		try {
			response.sendRedirect("/index");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * �α׾ƿ�
	 */
	@RequestMapping(value="/logout")
	public void logout(HttpSession session, HttpServletResponse response) {
		
		
		if(session.getAttribute("userLoginInfo") != null) {
			session.invalidate();
		}
		
		try {
			response.sendRedirect("/index");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * App User ���� insert
	 */
	@RequestMapping(value="/appUser", method=RequestMethod.GET)
	public void insertAppUser(HttpServletRequest request) {
		
		String custId   = request.getParameter("custId");
		String phoneNum = request.getParameter("phoneNum");
		String device   = request.getParameter("device");
		String osVer    = request.getParameter("osVer");
		String appVer   = request.getParameter("appVer");
		
		int check = 0;
		
		logger.debug("custId   : " + custId);
		logger.debug("phoneNum : " + phoneNum);
		logger.debug("device   : " + device);
		logger.debug("osVer    : " + osVer);
		logger.debug("appVer   : " + appVer);
		
		check = userService.checkCust(custId);
		
		if(check > 0) {
			userService.updateAppUser(custId, phoneNum, device, osVer, appVer);
		} else {
			userService.insertAppUser(custId, phoneNum, device, osVer, appVer);
		}

	}
}
