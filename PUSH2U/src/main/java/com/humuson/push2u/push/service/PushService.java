package com.humuson.push2u.push.service;

import java.util.List;
import java.util.Map;

/**
 * push ���� ����Ͻ� �Ծ�(�߻�޼ҵ�) ����
 * @author Ȳ����
 *
 */

public interface PushService {
	
	// �ֱ� Ǫ�� ��� ��������
	public List<Map<String, Object>> getRecentList(String userId, String msgType) throws RuntimeException;
	
	// Ǫ�� ���ø� ��������
	public Map<String, String> getRecentTemplete(String camId) throws RuntimeException;
	
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
	
	// SMS �α� �����ٷ�
	public void getSmsLogScheduler() throws RuntimeException;
	
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
	
	// report SMS ���� ����� ��������
	public List<Map<String, Object>> getSmsSuccessList(String userId, int camId, int limit) throws RuntimeException;
	
	// report SMS ���� ����� ��������
	public List<Map<String, Object>> getSmsFailList(String userId, int camId, int limit) throws RuntimeException;
	
	// report SMS ���� ����� �� ����
	public int smsSuccessSize(String userId, int camId) throws RuntimeException;
	
	// report SMS ���� ����� �� ����
	public int smsFailSize(String userId, int camId) throws RuntimeException;
	
	// ���� sms_detail MAX MSG_ID ���̵� ��������
	public int getMaxMsgId() throws RuntimeException;
	
	// ������Ʈ �α� ��������
	public List<Map<String, Object>> getSmsLogList(int maxMsgId) throws RuntimeException;
	
	// sms_detail �α� ������Ʈ
	public void updateSmsDetail(Map<String, Object> map) throws RuntimeException;
	
	// CLICK_DETAIL �α� �����ٷ�
	public void getClickLogScheduler() throws RuntimeException;
	
	// ���� push_click_detail MAX CLICK_ID ���̵� ��������
	public int getMaxClickId() throws RuntimeException;
	
	// PMS TB_CLICK ���̺� �α� ��������
	public List<Map<String, Object>> getClickLogList(int maxMsgId) throws RuntimeException;
	
	// push_click_detail �α� �μ�Ʈ
	public void insertClickDetail(Map<String, Object> map) throws RuntimeException;
	
	// Click �ξ۸޼��� �� ��������
	public List<Map<String, Object>> getClickMessageList(int camId) throws RuntimeException;
	
	// Click �˾� �� ��������
	public List<Map<String, Object>> getClickPopupList(int camId) throws RuntimeException;
	
	// Click IMG_URL �μ�Ʈ
	public void insertImgDetail(int camId, String pushType, int linkSeq, String imgUrl) throws RuntimeException;
	
	// �׷��� ���� ��� ��������
	public Map<String, Object> getOpenGraph(String camId) throws RuntimeException;
	
	// �׷��� Ŭ�� ��� ��������
	public Map<String, Object> getClickGraph(String camId) throws RuntimeException;
}
