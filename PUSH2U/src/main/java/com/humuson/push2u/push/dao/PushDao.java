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
	
}
