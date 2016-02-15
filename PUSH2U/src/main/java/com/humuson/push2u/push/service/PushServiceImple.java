package com.humuson.push2u.push.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
	
	// 문자발송 DB
	@Autowired
	private SqlSessionFactory sqlSessionFactory2;
	
	public void setSqlSessionFactory2(SqlSessionFactory sqlSessionFactory2) {
		this.sqlSessionFactory2 = sqlSessionFactory2;
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
			String inAppcontents, String smsYN, String smsContents, String phoneNum, int targetCnt) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("msgType", msgType);
		map.put("pushTitle", pushTitle);
		map.put("popupContents", popupContents);
		map.put("pushMsg", pushMsg);
		map.put("inAppcontents", inAppcontents);
		map.put("smsYN", smsYN);
		map.put("smsContents", smsContents);
		map.put("phoneNum", phoneNum);
		map.put("targetCnt", targetCnt);
		
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

	// 푸시 로그 스케줄러
	@Scheduled(fixedDelay = 60000)
	@Override
	public void getPushLogSchedular() throws RuntimeException {
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   푸시 로그 스케줄러 실행   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
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
				dao.plusResultCnt(map);
			}
			
			logger.debug("==========================     MAX 푸시 아이디 : " + maxPushId);
			logger.debug("==========================     가져온 푸시 LOG 개수 : " + logList.size());
			
		} finally {
			session.close();
			session3.close();
		}
		
	}
	
	
	// report 리스트 가져오기
	@Override
	public List<Map<String, Object>> getReportList(String userId, int limit) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("limit", limit);
		
		return pushDao.getReportList(map);
	}
	
	// report 총 개수
	@Override
	public int allReportSize(String userId) throws RuntimeException {
		return pushDao.allReportSize(userId);
	}
	
	// report Detail 가져오기
	@Override
	public Map<String, Object> getDetailReport(String userId, int camId) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		
		return pushDao.getDetailReport(map);
	}
	
	// report 타겟 대상자 가져오기
	@Override
	public List<Map<String, Object>> getTargetList(String userId, int camId, int limit) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		
		return pushDao.getTargetList(map);
	}
	
	// report 총 개수
	@Override
	public int allTargetSize(String userId, int camId) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		
		return pushDao.allTargetSize(map);
	}

	// SMS 발송 스케줄러
	@Scheduled(fixedDelay = 60000)
	@Override
	public void sendSmsScheduler() throws RuntimeException {
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   문자 발송 스케줄러 실행   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		int maxDetailId = 0;
		
		SqlSession session  = null;
		SqlSession session2 = null;
		
		try {
			
			session  = sqlSessionFactory.openSession();
			session2 = sqlSessionFactory2.openSession();
			
			PushDao dao  = session.getMapper(PushDao.class);
			PushDao dao2 = session2.getMapper(PushDao.class);
			
			maxDetailId = dao.getMaxDetailId();
			
			List<Map<String, Object>> smsList = null;
			
			smsList = dao.getSMSInfo(maxDetailId);
			
			logger.debug("==========================     MAX 디테일 아이디 : " + maxDetailId);
			logger.debug("==========================     발송할 SMS 개수 : " + smsList.size());
			
			for (Map<String, Object> map : smsList) {
				
				int detailId = Integer.parseInt(map.get("DETAIL_ID").toString());
				
				dao2.insertSMS(map);
				dao.insertSmsDetail(map);
				dao.updateSmsFlag(detailId);
			}
		
		} finally {
			session.close();
			session2.close();
		}
		
	}
	
	// report 성공 대상자 가져오기
	@Override
	public List<Map<String, Object>> getSuccessList(String userId, int camId, int limit) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		
		return pushDao.getSuccessList(map);
	}
	
	// report 성공 총 개수
	@Override
	public int allSuccessSize(String userId, int camId) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		
		return pushDao.allSuccessSize(map);
	}
	
	// report 오픈 대상자 가져오기
	@Override
	public List<Map<String, Object>> getOpenList(String userId, int camId, int limit) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		
		return pushDao.getOpenList(map);
	}
	
	// report 오픈 총 개수
	@Override
	public int allOpenSize(String userId, int camId) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		
		return pushDao.allOpenSize(map);
	}
	
	// report 미오픈 대상자 가져오기
	@Override
	public List<Map<String, Object>> getNoOpenList(String userId, int camId, int limit) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		
		return pushDao.getNoOpenList(map);
	}
	
	// report 미오픈 총 개수
	@Override
	public int allNoOpenSize(String userId, int camId) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		
		return pushDao.allNoOpenSize(map);
	}
	
}
