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
	
	// 최근 푸시 목록 가져오기
	@Override
	public List<Map<String, Object>> getRecentList(String userId, String msgType) throws RuntimeException {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("userId", userId);
		map.put("msgType", msgType);
		
		return pushDao.getRecentList(map);
	}
	
	// 푸시 템플릿 가져오기
	@Override
	public Map<String, String> getRecentTemplete(String camId) throws RuntimeException {
		
		return pushDao.getRecentTemplete(Integer.parseInt(camId));
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
	@Scheduled(fixedDelay = 64000)
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
	public List<Map<String, Object>> getReportList(String userId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getReportList(map);
	}
	
	// report 총 개수
	@Override
	public int allReportSize(String userId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("searchValue", searchValue);
		
		return pushDao.allReportSize(map);
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
	public List<Map<String, Object>> getTargetList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getTargetList(map);
	}
	
	// report 총 개수
	@Override
	public int allTargetSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.allTargetSize(map);
	}

	// SMS 발송 스케줄러
	@Scheduled(fixedDelay = 64000)
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
	
	
	// 로컬 sms_detail MAX MSG_ID 아이디 가져오기
	@Override
	public int getMaxMsgId() throws RuntimeException {
		
		return pushDao.getMaxMsgId();
	}
	
	// SMS 로그 스케줄러
	@Scheduled(fixedDelay = 20000)
	@Override  
	public void getSmsLogScheduler() throws RuntimeException {
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   문자 로그 스케줄러 실행   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		int maxMsgId = 0;
		
		SqlSession session  = null;
		SqlSession session2 = null;
		
		try {
			
			session  = sqlSessionFactory.openSession();
			session2 = sqlSessionFactory2.openSession();
			
			PushDao dao  = session.getMapper(PushDao.class);
			PushDao dao2 = session2.getMapper(PushDao.class);
			
			maxMsgId = dao.getMaxMsgId();
			
			List<Map<String, Object>> smsList = null;
			
			smsList = dao2.getSmsLogList(maxMsgId);
			
			logger.debug("==========================     MAX 메세지 아이디 : " + maxMsgId);
			logger.debug("==========================     가져온 SMS LIST 개수 : " + smsList.size());
			
			for (Map<String, Object> map : smsList) {
				
				dao.updateSmsDetail(map);
				dao.plusSmsCnt(map);
			}
		
		} finally {
			session.close();
			session2.close();
		}
		
	}
	
	// 아이하트 SMS 로그 가져오기
	@Override
	public List<Map<String, Object>> getSmsLogList(int maxMsgId) throws RuntimeException {
		
		return pushDao.getSmsLogList(maxMsgId);
		
	}
	
	// sms_detail 로그 업데이트
	@Override
	public void updateSmsDetail(Map<String, Object> map) throws RuntimeException {
				
		pushDao.updateSmsDetail(map);
		
	}
	
	
	
	// 로컬 puch_click_detail MAX CLICK_ID 아이디 가져오기
	@Override
	public int getMaxClickId() throws RuntimeException {
		
		return pushDao.getMaxClickId();
	}
	
	// CLICK_DETAIL 로그 스케줄러
	@Scheduled(fixedDelay = 64000)
	@Override  
	public void getClickLogScheduler() throws RuntimeException {
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   클릭 로그 스케줄러 실행   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		int maxClickId = 0;
		
		SqlSession session  = null;
		SqlSession session3 = null;
		
		try {
			
			session  = sqlSessionFactory.openSession();
			session3 = sqlSessionFactory3.openSession();
			
			PushDao dao  = session.getMapper(PushDao.class);
			PushDao dao3 = session3.getMapper(PushDao.class);
			
			maxClickId = dao.getMaxClickId();
			
			List<Map<String, Object>> clickList = null;
			
			clickList = dao3.getClickLogList(maxClickId);
			
			logger.debug("==========================     MAX 클릭 아이디 : " + maxClickId);
			logger.debug("==========================     가져온 CLICK LIST 개수 : " + clickList.size());
			
			for (Map<String, Object> map : clickList) {
				
				dao.insertClickDetail(map);
			}
		
		} finally {
			session.close();
			session3.close();
		}
		
	}
	
	// PMS CLICK DETAIL 로그 가져오기
	@Override
	public List<Map<String, Object>> getClickLogList(int maxClickId) throws RuntimeException {
		
		return pushDao.getClickLogList(maxClickId);
		
	}
	
	// push_click_detail 로그 인서트
	@Override
	public void insertClickDetail(Map<String, Object> map) throws RuntimeException {
				
		pushDao.insertClickDetail(map);
		
	}
	
	
	
	
	
	
	
	// report 성공 대상자 가져오기
	@Override
	public List<Map<String, Object>> getSuccessList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getSuccessList(map);
	}
	
	// report 성공 총 개수
	@Override
	public int allSuccessSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.allSuccessSize(map);
	}
	
	// report 실패 대상자 가져오기
	@Override
	public List<Map<String, Object>> getFailList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getFailList(map);
	}
	
	// report 실패 총 개수
	@Override
	public int allFailSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.allFailSize(map);
	}
	
	// report 오픈 대상자 가져오기
	@Override
	public List<Map<String, Object>> getOpenList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getOpenList(map);
	}
	
	// report 오픈 총 개수
	@Override
	public int allOpenSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.allOpenSize(map);
	}
	
	// report 미오픈 대상자 가져오기
	@Override
	public List<Map<String, Object>> getNoOpenList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getNoOpenList(map);
	}
	
	// report 미오픈 총 개수
	@Override
	public int allNoOpenSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.allNoOpenSize(map);
	}

	// report SMS 성공 대상자 가져오기
	@Override
	public List<Map<String, Object>> getSmsSuccessList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getSmsSuccessList(map);
	}
	
	// report SMS 성공 대상사 총 개수
	@Override
	public int smsSuccessSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.smsSuccessSize(map);
	}
	
	// report SMS 실패 대상자 가져오기
	@Override
	public List<Map<String, Object>> getSmsFailList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getSmsFailList(map);
	}

	// report SMS 실패 대상자 총 개수
	@Override
	public int smsFailSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.smsFailSize(map);
		
	}
	
	// Click 인앱메세지 상세 가져오기
	@Override
	public List<Map<String, Object>> getClickMessageList(int camId) throws RuntimeException {
		
//		Map<String, Object> map = new HashMap<String, Object>();
		
		return pushDao.getClickMessageList(camId);
	}
	
	// Click 팝업 상세 가져오기
	@Override
	public List<Map<String, Object>> getClickPopupList(int camId) throws RuntimeException {
		
//			Map<String, Object> map = new HashMap<String, Object>();
		
		return pushDao.getClickPopupList(camId);
	}
	
	// push_click_detail 로그 인서트
	@Override
	public void insertImgDetail(int camId, String pushType, int linkSeq, String imgUrl) throws RuntimeException {
				
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("camId", camId);
		map.put("pushType", pushType);
		map.put("linkSeq", linkSeq);
		map.put("imgUrl", imgUrl);
		
		pushDao.insertImgDetail(map);
		
	}

	// 그래프 오픈 통계 가져오기
	@Override
	public Map<String, Object> getOpenGraph(String camId) throws RuntimeException {
		
		return pushDao.getOpenGraph(Integer.parseInt(camId));
	}
	
	// 그래프 클릭 통계 가져오기
	@Override
	public Map<String, Object> getClickGraph(String camId) throws RuntimeException {
		
		return pushDao.getClickGraph(Integer.parseInt(camId));
	}
	
	// 그래프 전체 오픈 통계 가져오기
	@Override
	public Map<String, Object> getTotalOpenGraph() throws RuntimeException {
		
		return pushDao.getTotalOpenGraph();
	}
	
	// 그래프 전체 클릭 통계 가져오기
	@Override
	public Map<String, Object> getTotalClickGraph() throws RuntimeException {
		
		return pushDao.getTotalClickGraph();
	}

}
