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
	
	// 캠페인 정보 isnert
	public void insertCampaign(Map<String, String> map) throws RuntimeException;
	
}
