package com.humuson.push2u.push.service;

import java.util.List;
import java.util.Map;

/**
 * push ���� ����Ͻ� �Ծ�(�߻�޼ҵ�) ����
 * @author Ȳ����
 *
 */

public interface PushService {
	
	// App User ��� ��������
	public List<Map<String, String>> getAppUserList() throws RuntimeException;
	
	// MAX Ǫ�� ���̵� ��������
	public int getMaxCamId() throws RuntimeException;
	
	// ķ���� ���� insert
	public void insertCampaign(String userId, String msgType, String pushTitle, 
			String popupContents, String pushMsg, String inAppcontents, String smsYN, String smsContents, String phoneNum, int targetcnt) throws RuntimeException;
	
	// ķ���� detail insert
	public void insertPushDetail(List<Map<String, Object>> detailList) throws RuntimeException;
	
	// Ǫ�� �α� �����ٷ�
	public void getPushLogSchedular() throws RuntimeException;
	
	// report ����Ʈ ��������
	public List<Map<String, Object>> getReportList(String userId, int limit) throws RuntimeException;
		
	// report �� ���� 
	public int allReportSize(String userId) throws RuntimeException;
	
	// report Detail ��������
	public Map<String, Object> getDetailReport(String userId, int camId) throws RuntimeException;
	
	// report Ÿ�� ����� ��������
	public List<Map<String, Object>> getTargetList(String userId, int camId, int limit) throws RuntimeException;
	
	// report Ÿ�� ����� �� ���� 
	public int allTargetSize(String userId, int camId) throws RuntimeException;
	
	// SMS �߼� �����ٷ�
	public void sendSmsScheduler() throws RuntimeException;
	
	// report ���� ����� ��������
	public List<Map<String, Object>> getSuccessList(String userId, int camId, int limit) throws RuntimeException;
	
	// report ���� ����� �� ���� 
	public int allSuccessSize(String userId, int camId) throws RuntimeException;
	
	// report ���� ����� ��������
	public List<Map<String, Object>> getFailList(String userId, int camId, int limit) throws RuntimeException;
	
	// report ���� ����� �� ����
	public int allFailSize(String userId, int camId) throws RuntimeException;
	
	// report ���� ����� ��������
	public List<Map<String, Object>> getOpenList(String userId, int camId, int limit) throws RuntimeException;
	
	// report ���� ����� �� ���� 
	public int allOpenSize(String userId, int camId) throws RuntimeException;
	
	// report �� ���� ����� ��������
	public List<Map<String, Object>> getNoOpenList(String userId, int camId, int limit) throws RuntimeException;
	
	// report �� ���� ����� �� ���� 
	public int allNoOpenSize(String userId, int camId) throws RuntimeException;
	
}
