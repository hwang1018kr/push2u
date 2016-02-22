package com.humuson.push2u.user.dao;

import java.util.Map;

import com.humuson.push2u.user.domain.User;

/**
 * ȸ�� ���� ó�� �Ծ� ����
 * @author Ȳ����
 *
 */

public interface UserDao {
	
	// ȸ�� �α���
	public User login(Map<String, String> userMap) throws RuntimeException;
	
	// ���̵�� ȸ�� ã��
	public int findUserById(String userId) throws RuntimeException;
	
	// �������� ����
	public void authPhoneNum(Map<String, String> authMap) throws RuntimeException;
	
	// ȸ������
	public void join(Map<String, String> joinMap) throws RuntimeException;
	
	// custId üũ
	public int checkCust(String custId) throws RuntimeException;
	
	// App User update
	public void updateAppUser(Map<String, String> appUserMap) throws RuntimeException;
	
	// App User insert
	public void insertAppUser(Map<String, String> appUserMap) throws RuntimeException;
}
