package com.humuson.push2u.push.service;

import java.util.List;
import java.util.Map;

/**
 * push 관련 비즈니스 규약(추상메소드) 선언
 * @author 황집중
 *
 */

public interface PushService {
	
	// App User 목록 가져오기
	public List<Map<String, String>> getAppUserList() throws RuntimeException;
	
	// MAX 푸시 아이디 가져오기
	public int getMaxCamId() throws RuntimeException;
	
	// 캠페인 정보 insert
	public void insertCampaign(String userId, String msgType, String pushTitle, 
			String popupContents, String pushMsg, String inAppcontents, String smsYN, int targetcnt) throws RuntimeException;
	
	// 캠페인 detail insert
	public void insertPushDetail(List<Map<String, Object>> detailList) throws RuntimeException;
	
	// 푸시 로그 스케줄러
	public void getPushLogSchedular() throws RuntimeException;
	
	// report 리스트 가져오기
	public List<Map<String, Object>> getReportList(String userId, int limit) throws RuntimeException;
		
	// report 총 개수 
	public int allReportSize(String userId) throws RuntimeException;
	
	// report Detail 가져오기
	public List<Map<String, Object>> getDetailReport(int camId) throws RuntimeException;
	
	// SMS 발송 스케줄러
	public void sendSmsScheduler() throws RuntimeException;
	
}
