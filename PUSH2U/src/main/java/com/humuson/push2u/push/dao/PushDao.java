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
	
	// ķ���� ����� ī��Ʈ ����
	public void plusResultCnt(Map<String, Object> map) throws RuntimeException;
	
	// report ����Ʈ ��������
	public List<Map<String, Object>> getReportList(Map<String, Object> map) throws RuntimeException;
	
	// report �� ����
	public int allReportSize(String userId) throws RuntimeException;
	
	// report Detail ��������
	public Map<String, Object> getDetailReport(Map<String, Object> map) throws RuntimeException;

	// report Ÿ�� ����� ��������
	public List<Map<String, Object>> getTargetList(Map<String, Object> map) throws RuntimeException;

	// report Ÿ�� ����� �� ����
	public int allTargetSize(Map<String, Object> map) throws RuntimeException;
	
	// MAX detail_id ��������
	public int getMaxDetailId() throws RuntimeException;
	
	// report ���� ����� ��������
	public List<Map<String, Object>> getSuccessList(Map<String, Object> map) throws RuntimeException;

	// report ���� ����� �� ����
	public int allSuccessSize(Map<String, Object> map) throws RuntimeException;
	
	// report ���� ����� ��������
	public List<Map<String, Object>> getFailList(Map<String, Object> map) throws RuntimeException; 
	
	// report ���� ����� �� ����
	public int allFailSize(Map<String, Object> map) throws RuntimeException;
	
	// report ���� ����� ��������
	public List<Map<String, Object>> getOpenList(Map<String, Object> map) throws RuntimeException;

	// report ���� ����� �� ����
	public int allOpenSize(Map<String, Object> map) throws RuntimeException;
	
	// report �̿��� ����� ��������
	public List<Map<String, Object>> getNoOpenList(Map<String, Object> map) throws RuntimeException;

	// report �̿��� ����� �� ����
	public int allNoOpenSize(Map<String, Object> map) throws RuntimeException;
	
	// SMS �߼��� ���� ��������
	public List<Map<String, Object>> getSMSInfo(int maxDetailId) throws RuntimeException;
	
	// SMS �߼�DB INSERT
	public void insertSMS(Map<String, Object> map) throws RuntimeException;
	
	// SMS DETAIL INSERT
	public void insertSmsDetail(Map<String, Object> map) throws RuntimeException;
	
	// SMS �߼� �÷��� UPDATE
	public void updateSmsFlag(int detailId) throws RuntimeException;
	
	// ���� sms_detail ���̺� MAX msg_id ��������
	public int getMaxMsgId() throws RuntimeException;
	
	// ������Ʈ SMS �α� ��������
	public List<Map<String, Object>> getSmsLogList(int maxMsgId) throws RuntimeException;
	
	// ���� sms_detail �α� UPDATE
	public void updateSmsDetail(Map<String, Object> map) throws RuntimeException;
}
