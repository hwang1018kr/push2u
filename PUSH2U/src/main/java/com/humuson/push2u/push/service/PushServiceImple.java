package com.humuson.push2u.push.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humuson.push2u.push.controller.PushController;
import com.humuson.push2u.push.dao.PushDao;

@Service("pushService")
public class PushServiceImple implements PushService {

	private static final Logger logger = LoggerFactory.getLogger(PushController.class);
	
	@Autowired
	private PushDao pushDao;
	
	public PushServiceImple(){}
	
	public PushDao getPushDao() {
		return pushDao;
	}
	
	public void setPushDao(PushDao pushDao) {
		this.pushDao = pushDao;
	}

	// App User 목록 가져오기
	@Override
	public List<Map<String, String>> getAppUserList() throws RuntimeException {
		
		return pushDao.getAppUserList();
		
	}
	
	// MAX 푸시아이디 가져오기
	@Override
	public int getMaxCamId() throws RuntimeException {
		
		return pushDao.getMaxCamId();
	}

	// 캠페인 정보 insert
	@Override
	public void insertCampaign(String userId, String msgType, String pushTitle, String popupContents, String pushMsg,
			String inAppcontents, String smsYN) throws RuntimeException {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("userId", userId);
		map.put("msgType", msgType);
		map.put("pushTitle", pushTitle);
		map.put("popupContents", popupContents);
		map.put("pushMsg", pushMsg);
		map.put("inAppcontents", inAppcontents);
		map.put("smsYN", smsYN);
		
		pushDao.insertCampaign(map);
		
	}

	
	
	
}
