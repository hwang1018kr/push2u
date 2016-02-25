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
	
	// �ֱ� Ǫ�� ��� ��������
	@Override
	public List<Map<String, Object>> getRecentList(String userId, String msgType) throws RuntimeException {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("userId", userId);
		map.put("msgType", msgType);
		
		return pushDao.getRecentList(map);
	}
	
	// Ǫ�� ���ø� ��������
	@Override
	public Map<String, String> getRecentTemplete(String camId) throws RuntimeException {
		
		return pushDao.getRecentTemplete(Integer.parseInt(camId));
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

	// Ǫ�� �α� �����ٷ�
	@Scheduled(fixedDelay = 64000)
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
				dao.plusResultCnt(map);
			}
			
			logger.debug("==========================     MAX Ǫ�� ���̵� : " + maxPushId);
			logger.debug("==========================     ������ Ǫ�� LOG ���� : " + logList.size());
			
		} finally {
			session.close();
			session3.close();
		}
		
	}
	
	// report ����Ʈ ��������
	@Override
	public List<Map<String, Object>> getReportList(String userId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getReportList(map);
	}
	
	// report �� ����
	@Override
	public int allReportSize(String userId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("searchValue", searchValue);
		
		return pushDao.allReportSize(map);
	}
	
	// report Detail ��������
	@Override
	public Map<String, Object> getDetailReport(String userId, int camId) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		
		return pushDao.getDetailReport(map);
	}
	
	// report Ÿ�� ����� ��������
	@Override
	public List<Map<String, Object>> getTargetList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getTargetList(map);
	}
	
	// report �� ����
	@Override
	public int allTargetSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.allTargetSize(map);
	}

	// SMS �߼� �����ٷ�
	@Scheduled(fixedDelay = 64000)
	@Override
	public void sendSmsScheduler() throws RuntimeException {
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   ���� �߼� �����ٷ� ����   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
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
			
			logger.debug("==========================     MAX ������ ���̵� : " + maxDetailId);
			logger.debug("==========================     �߼��� SMS ���� : " + smsList.size());
			
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
	
	
	// ���� sms_detail MAX MSG_ID ���̵� ��������
	@Override
	public int getMaxMsgId() throws RuntimeException {
		
		return pushDao.getMaxMsgId();
	}
	
	// SMS �α� �����ٷ�
	@Scheduled(fixedDelay = 20000)
	@Override  
	public void getSmsLogScheduler() throws RuntimeException {
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   ���� �α� �����ٷ� ����   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
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
			
			logger.debug("==========================     MAX �޼��� ���̵� : " + maxMsgId);
			logger.debug("==========================     ������ SMS LIST ���� : " + smsList.size());
			
			for (Map<String, Object> map : smsList) {
				
				dao.updateSmsDetail(map);
				dao.plusSmsCnt(map);
			}
		
		} finally {
			session.close();
			session2.close();
		}
		
	}
	
	// ������Ʈ SMS �α� ��������
	@Override
	public List<Map<String, Object>> getSmsLogList(int maxMsgId) throws RuntimeException {
		
		return pushDao.getSmsLogList(maxMsgId);
		
	}
	
	// sms_detail �α� ������Ʈ
	@Override
	public void updateSmsDetail(Map<String, Object> map) throws RuntimeException {
				
		pushDao.updateSmsDetail(map);
		
	}
	
	
	
	// ���� puch_click_detail MAX CLICK_ID ���̵� ��������
	@Override
	public int getMaxClickId() throws RuntimeException {
		
		return pushDao.getMaxClickId();
	}
	
	// CLICK_DETAIL �α� �����ٷ�
	@Scheduled(fixedDelay = 64000)
	@Override  
	public void getClickLogScheduler() throws RuntimeException {
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   Ŭ�� �α� �����ٷ� ����   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
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
			
			logger.debug("==========================     MAX Ŭ�� ���̵� : " + maxClickId);
			logger.debug("==========================     ������ CLICK LIST ���� : " + clickList.size());
			
			for (Map<String, Object> map : clickList) {
				
				dao.insertClickDetail(map);
			}
		
		} finally {
			session.close();
			session3.close();
		}
		
	}
	
	// PMS CLICK DETAIL �α� ��������
	@Override
	public List<Map<String, Object>> getClickLogList(int maxClickId) throws RuntimeException {
		
		return pushDao.getClickLogList(maxClickId);
		
	}
	
	// push_click_detail �α� �μ�Ʈ
	@Override
	public void insertClickDetail(Map<String, Object> map) throws RuntimeException {
				
		pushDao.insertClickDetail(map);
		
	}
	
	
	
	
	
	
	
	// report ���� ����� ��������
	@Override
	public List<Map<String, Object>> getSuccessList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getSuccessList(map);
	}
	
	// report ���� �� ����
	@Override
	public int allSuccessSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.allSuccessSize(map);
	}
	
	// report ���� ����� ��������
	@Override
	public List<Map<String, Object>> getFailList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getFailList(map);
	}
	
	// report ���� �� ����
	@Override
	public int allFailSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.allFailSize(map);
	}
	
	// report ���� ����� ��������
	@Override
	public List<Map<String, Object>> getOpenList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getOpenList(map);
	}
	
	// report ���� �� ����
	@Override
	public int allOpenSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.allOpenSize(map);
	}
	
	// report �̿��� ����� ��������
	@Override
	public List<Map<String, Object>> getNoOpenList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getNoOpenList(map);
	}
	
	// report �̿��� �� ����
	@Override
	public int allNoOpenSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.allNoOpenSize(map);
	}

	// report SMS ���� ����� ��������
	@Override
	public List<Map<String, Object>> getSmsSuccessList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getSmsSuccessList(map);
	}
	
	// report SMS ���� ���� �� ����
	@Override
	public int smsSuccessSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.smsSuccessSize(map);
	}
	
	// report SMS ���� ����� ��������
	@Override
	public List<Map<String, Object>> getSmsFailList(String userId, int camId, int limit, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("limit", limit);
		map.put("searchValue", searchValue);
		
		return pushDao.getSmsFailList(map);
	}

	// report SMS ���� ����� �� ����
	@Override
	public int smsFailSize(String userId, int camId, String searchValue) throws RuntimeException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("camId", camId);
		map.put("searchValue", searchValue);
		
		return pushDao.smsFailSize(map);
		
	}
	
	// Click �ξ۸޼��� �� ��������
	@Override
	public List<Map<String, Object>> getClickMessageList(int camId) throws RuntimeException {
		
//		Map<String, Object> map = new HashMap<String, Object>();
		
		return pushDao.getClickMessageList(camId);
	}
	
	// Click �˾� �� ��������
	@Override
	public List<Map<String, Object>> getClickPopupList(int camId) throws RuntimeException {
		
//			Map<String, Object> map = new HashMap<String, Object>();
		
		return pushDao.getClickPopupList(camId);
	}
	
	// push_click_detail �α� �μ�Ʈ
	@Override
	public void insertImgDetail(int camId, String pushType, int linkSeq, String imgUrl) throws RuntimeException {
				
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("camId", camId);
		map.put("pushType", pushType);
		map.put("linkSeq", linkSeq);
		map.put("imgUrl", imgUrl);
		
		pushDao.insertImgDetail(map);
		
	}

	// �׷��� ���� ��� ��������
	@Override
	public Map<String, Object> getOpenGraph(String camId) throws RuntimeException {
		
		return pushDao.getOpenGraph(Integer.parseInt(camId));
	}
	
	// �׷��� Ŭ�� ��� ��������
	@Override
	public Map<String, Object> getClickGraph(String camId) throws RuntimeException {
		
		return pushDao.getClickGraph(Integer.parseInt(camId));
	}
	
	// �׷��� ��ü ���� ��� ��������
	@Override
	public Map<String, Object> getTotalOpenGraph() throws RuntimeException {
		
		return pushDao.getTotalOpenGraph();
	}
	
	// �׷��� ��ü Ŭ�� ��� ��������
	@Override
	public Map<String, Object> getTotalClickGraph() throws RuntimeException {
		
		return pushDao.getTotalClickGraph();
	}

}
