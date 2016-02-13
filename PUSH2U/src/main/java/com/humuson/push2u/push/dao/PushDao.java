package com.humuson.push2u.push.dao;

import java.util.List;
import java.util.Map;

/**
 * PUSH ���� ó�� �Ծ� ����
 * @author Ȳ����
 *
 */

public interface PushDao {

	// App User ��� ��������
	public List<Map<String, String>> getAppUserList() throws RuntimeException;
	
	// MAX ķ���� ���̵� ��������
	public int getMaxCamId() throws RuntimeException;
	
	// MAX Ǫ�� ���̵� ��������
	public int getMaxPushId() throws RuntimeException;
	
	// ķ���� ���� isnert
	public void insertCampaign(Map<String, Object> map) throws RuntimeException;
	
	// ķ���� ������ insert
	public void insertPushDetail(Map<String, Object> map) throws RuntimeException;
	
	// Ǫ�� �α� ��������
	public List<Map<String, Object>> getPushDetailList(int maxPushId) throws RuntimeException;
	
	// ���� DB�� Ǫ�� �α� INSERT
	public void insertPushLog(Map<String, Object> map) throws RuntimeException;
	
	// report ����Ʈ ��������
	public List<Map<String, Object>> getReportList(Map<String, Object> map) throws RuntimeException;
	
	// report �� ����
	public int allReportSize(String userId) throws RuntimeException;
	
	// report Detail ��������
	public List<Map<String, Object>> getDetailReport(int camId) throws RuntimeException;
	
	
}
