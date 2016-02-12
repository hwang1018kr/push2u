package com.humuson.push2u.user.service;

import com.humuson.push2u.user.domain.User;

/**
 * ȸ�� ���� ����Ͻ� �Ծ�(�߻�޼ҵ�) ����
 * @author Ȳ����
 *
 */

public interface UserService {
	
	// ȸ�� �α���
	public User login(String userId, String userPasswd) throws RuntimeException;
	
	// ���̵� �ߺ��˻�
	public int idCheck(String userId) throws RuntimeException;  
	
	// ������ȣ ����
	public void authPhoneNum(String phoneNum, String authNum) throws RuntimeException;
	
	// ȸ������
	public void join(String userId, String userPasswd, String userName, String phoneNum) throws RuntimeException;
	
	// App User insert
	public void insertAppUser(String custId, String phoneNum, String device, String osVer, String appVer) throws RuntimeException;
	
}
