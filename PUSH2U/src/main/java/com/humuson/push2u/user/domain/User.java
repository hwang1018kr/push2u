package com.humuson.push2u.user.domain;

/**
 * User Domain
 * @author È²ÁýÁß
 *
 */

public class User {
	
	private String userId;
	private String userName;
	private String userPasswd;
	private String joinDate;
	private String phoneNum;
	
	public User() {
	
	}

	public User(String userId, String userName, String userPasswd, String joinDate, String phoneNum) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPasswd = userPasswd;
		this.joinDate = joinDate;
		this.phoneNum = phoneNum;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPasswd() {
		return userPasswd;
	}


	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}


	public String getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPasswd=" + userPasswd + ", joinDate="
				+ joinDate + ", phoneNum=" + phoneNum + "]";
	}
	
}
