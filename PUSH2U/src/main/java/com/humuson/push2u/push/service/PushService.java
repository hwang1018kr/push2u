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
	
}
