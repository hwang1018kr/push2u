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
	
	// ���ڹ߼� DB
	@Autowired
	private SqlSessionFactory sqlSessionFactory2;
	
	public void setSqlSessionFactory2(SqlSessionFactory sqlSessionFactory2) {
		this.sqlSessionFactory2 = sqlSessionFactory2;
	}
	
	// Ǫ���Ǿ� DEV DB
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

	// App User ��� ��������
	@Override
	public List<Map<String, String>> getAppUserList() throws RuntimeException {
		
		return pushDao.getAppUserList();
		
	}
	
	// MAX Ǫ�þ��̵� ��������
	@Override
	public int getMaxCamId() throws RuntimeException {
		
		return pushDao.getMaxCamId();
	}

	// ķ���� ���� insert
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
		map.put("targetCnt", targetCnt);
		
		pushDao.insertCampaign(map);
		
	}

	// ķ���� detail insert
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

	// �α� �����ٷ�
	@Scheduled(fixedDelay = 60000)
	@Override
	public void getPushLogSchedular() throws RuntimeException {
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   Ǫ�� �α� �����ٷ� ����   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
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
			
			logger.debug("==========================     MAX Ǫ�� ���̵� : " + maxPushId);
			logger.debug("==========================     ������ LOG ���� : " + logList.size());
			
		} finally {
			session.close();
			session3.close();
		}
		
	}
	
	
	// report ����Ʈ ��������
	@Override
	public List<Map<String, Object>> getReportList(String userId, int limit) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("limit", limit);
		
		return pushDao.getReportList(map);
	}
	
	// report �� ����
	@Override
	public int allReportSize(String userId) throws RuntimeException {
		return pushDao.allReportSize(userId);
	}
	
	// report Detail ��������
	@Override
	public List<Map<String, Object>> getDetailReport(int camId) throws RuntimeException {
		return pushDao.getDetailReport(camId);
	}

	// SMS �߼� �����ٷ�
	@Scheduled(fixedDelay = 6000)
	@Override
	public void sendSmsScheduler() throws RuntimeException {
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   ���� �߼� �����ٷ� ����   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		int maxDetailId = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			PushDao dao = session.getMapper(PushDao.class);
			
			maxDetailId = dao.getMaxDetailId();
		
			logger.debug("==========================     MAX ������ ���̵� : " + maxDetailId);
			
		} finally {
			session.close();
		}
		
	}
	
}
