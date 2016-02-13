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
			String popupContents, String pushMsg, String inAppcontents, String smsYN, int targetcnt) throws RuntimeException;
	
	// ķ���� detail insert
	public void insertPushDetail(List<Map<String, Object>> detailList) throws RuntimeException;
	
	// �α� �����ٷ�
	public void getPushLogSchedular() throws RuntimeException;
}
