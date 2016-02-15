package com.humuson.push2u.push.dao;

import java.util.List;
import java.util.Map;

/**
 * PUSH 관련 처리 규약 선언
 * @author 황집중
 *
 */

public interface PushDao {

	// App User 목록 가져오기
	public List<Map<String, String>> getAppUserList() throws RuntimeException;
	
	// MAX 캠페인 아이디 가져오기
	public int getMaxCamId() throws RuntimeException;
	
	// MAX 푸시 아이디 가져오기
	public int getMaxPushId() throws RuntimeException;
	
	// 캠페인 정보 isnert
	public void insertCampaign(Map<String, Object> map) throws RuntimeException;
	
	// 캠페인 디테일 insert
	public void insertPushDetail(Map<String, Object> map) throws RuntimeException;
	
	// 푸시 로그 가져오기
	public List<Map<String, Object>> getPushDetailList(int maxPushId) throws RuntimeException;
	
	// 로컬 DB에 푸시 로그 INSERT
	public void insertPushLog(Map<String, Object> map) throws RuntimeException;
	
	// 캠페인 결과값 카운트 증가
	public void plusResultCnt(Map<String, Object> map) throws RuntimeException;
	
	// report 리스트 가져오기
	public List<Map<String, Object>> getReportList(Map<String, Object> map) throws RuntimeException;
	
	// report 총 개수
	public int allReportSize(String userId) throws RuntimeException;
	
	// report Detail 가져오기
	public Map<String, Object> getDetailReport(Map<String, Object> map) throws RuntimeException;

	// report 타겟 대상자 가져오기
	public List<Map<String, Object>> getTargetList(Map<String, Object> map) throws RuntimeException;

	// report 타겟 대상자 총 개수
	public int allTargetSize(Map<String, Object> map) throws RuntimeException;
	
	// MAX detail_id 가져오기
	public int getMaxDetailId() throws RuntimeException;
	
	// report 성공 대상자 가져오기
	public List<Map<String, Object>> getSuccessList(Map<String, Object> map) throws RuntimeException;

	// report 성공 대상자 총 개수
	public int allSuccessSize(Map<String, Object> map) throws RuntimeException;
	
	// report 실패 대상자 가져오기
	public List<Map<String, Object>> getFailList(Map<String, Object> map) throws RuntimeException; 
	
	// report 실패 대상자 총 개수
	public int allFailSize(Map<String, Object> map) throws RuntimeException;
	
	// report 오픈 대상자 가져오기
	public List<Map<String, Object>> getOpenList(Map<String, Object> map) throws RuntimeException;

	// report 오픈 대상자 총 개수
	public int allOpenSize(Map<String, Object> map) throws RuntimeException;
	
	// report 미오픈 대상자 가져오기
	public List<Map<String, Object>> getNoOpenList(Map<String, Object> map) throws RuntimeException;

	// report 미오픈 대상자 총 개수
	public int allNoOpenSize(Map<String, Object> map) throws RuntimeException;
	
	// SMS 발송할 정보 가져오기
	public List<Map<String, Object>> getSMSInfo(int maxDetailId) throws RuntimeException;
	
	// SMS 발송DB INSERT
	public void insertSMS(Map<String, Object> map) throws RuntimeException;
	
	// SMS DETAIL INSERT
	public void insertSmsDetail(Map<String, Object> map) throws RuntimeException;
	
	// SMS 발송 플래그 UPDATE
	public void updateSmsFlag(int detailId) throws RuntimeException;
	
	// 로컬 sms_detail 테이블 MAX msg_id 가져오기
	public int getMaxMsgId() throws RuntimeException;
	
	// 아이하트 SMS 로그 가져오기
	public List<Map<String, Object>> getSmsLogList(int maxMsgId) throws RuntimeException;
	
	// 로컬 sms_detail 로그 UPDATE
	public void updateSmsDetail(Map<String, Object> map) throws RuntimeException;
}
