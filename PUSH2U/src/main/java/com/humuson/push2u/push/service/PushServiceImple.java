package com.humuson.push2u.push.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.humuson.push2u.push.controller.PushController;
import com.humuson.push2u.push.dao.PushDao;

@Service("pushService")
public class PushServiceImple implements PushService {

	private static final Logger logger = LoggerFactory.getLogger(PushController.class);
	
	// Local DB
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	// 푸시피아 DEV DB
	@Autowired
	private SqlSessionFactory sqlSessionFactory3;
	
	public void setSqlSessionFactory3(SqlSessionFactory sqlSessionFactory3) {
		this.sqlSessionFactory3 = sqlSessionFactory3;
	}
	
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
			String inAppcontents, String smsYN, int targetCnt) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("msgType", msgType);
		map.put("pushTitle", pushTitle);
		map.put("popupContents", popupContents);
		map.put("pushMsg", pushMsg);
		map.put("inAppcontents", inAppcontents);
		map.put("smsYN", smsYN);
		map.put("targetcnt", targetCnt);
		
		pushDao.insertCampaign(map);
		
	}

	// 캠페인 detail insert
	@Override
	public void insertPushDetail(List<Map<String, Object>> detailList) throws RuntimeException {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			for (Map<String, Object> map : detailList) {
				
				dao.insertPushDetail(map);
				
			}
		} finally {
			session.close();
		}
		
	}

	// 로그 스케줄러
	@Scheduled(fixedDelay = 10000)
	@Override
	public void getPushLogSchedular() throws RuntimeException {
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   로그 스케줄러 실행   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		int maxPushId = 0;
		
		SqlSession session  = null;
		SqlSession session3 = null;
		
		try {
			session  = sqlSessionFactory.openSession();
			session3 = sqlSessionFactory3.openSession();
			
			PushDao dao  = session.getMapper(PushDao.class);
			PushDao dao3 = session3.getMapper(PushDao.class);
			
			maxPushId = dao.getMaxPushId();
			
			List<Map<String, Object>> logList = null;
			
			logList = dao3.getPushDetailList(maxPushId);
			
			for (Map<String, Object> map : logList) {
				
				String camId  = null;
				String str[]  = null;
				String reqUid = String.valueOf(map.get("REQ_UID"));
				
				str = reqUid.split("_");
				
				camId = str[0];
				
				map.put("CAM_ID", Integer.parseInt(camId));
				
				dao.insertPushLog(map);
			}
			
			logger.debug("==========================     MAX 푸시 아이디 : " + maxPushId);
			logger.debug("==========================     가져온 LOG 개수 : " + logList.size());
			
		} finally {
			session.close();
			session3.close();
		}
		
	}
	
}
