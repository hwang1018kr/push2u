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
	
}
