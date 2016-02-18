package com.humuson.push2u.push.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.humuson.push2u.push.controller.PushController;

@Repository("pushDao")
public class MyBatisPushDao implements PushDao {

	private static final Logger logger = LoggerFactory.getLogger(PushController.class);
	
	// Local DB
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	// 최근 푸시 목록 가져오기
	@Override
	public List<Map<String, Object>> getRecentList(Map<String, String> map) throws RuntimeException {
		
		List<Map<String, Object>> recentList = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			recentList = dao.getRecentList(map);
		} finally {
			session.close();
		}
		
		return recentList;
	}
	
	// 푸시 템플릿 가져오기
	@Override
	public Map<String, String> getRecentTemplete(int camId) throws RuntimeException {
		
		Map<String, String> templeteMap = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			templeteMap = dao.getRecentTemplete(camId);
		} finally {
			session.close();
		}
		
		return templeteMap;
	}

	// App User 목록 가져오기
	@Override
	public List<Map<String, String>> getAppUserList() throws RuntimeException {

		List<Map<String, String>> appUserList = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			appUserList = dao.getAppUserList();
			
		} finally {
			session.close();
		}
		
		return appUserList;
	}
	
	// MAX 캠페인 아이디 가져오기
	@Override
	public int getMaxCamId() throws RuntimeException {
		
		int maxPushId = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			maxPushId = dao.getMaxCamId();
			
		} finally {
			session.close();
		}
		
		return maxPushId;
	}
	
	// MAX 푸시 아이디 가져오기
	@Override
	public int getMaxPushId() throws RuntimeException {
		// TODO Auto-generated method stub
		return 0;
	}

	// 캠페인 정보 insert
	@Override
	public void insertCampaign(Map<String, Object> map) throws RuntimeException {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			dao.insertCampaign(map);
			
		} finally {
			session.close();
		}
		
	}

	// 캠페인 디테일 insert
	@Override
	public void insertPushDetail(Map<String, Object> map) throws RuntimeException {
		
	}

	// 푸시 로그 가져오기
	@Override
	public List<Map<String, Object>> getPushDetailList(int maxPushId) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	// 로컬 DB로 푸시 로그 INSERT
	@Override
	public void insertPushLog(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}
	
	
	// report 리스트 가져오기
	@Override
	public List<Map<String, Object>> getReportList(Map<String, Object> map) throws RuntimeException {
		
		List<Map<String, Object>> reportList = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			reportList = dao.getReportList(map);
			
		} finally {
			session.close();
		}
		
		return reportList;
	}
	
	// report 총 개수
	@Override
	public int allReportSize(String userId) throws RuntimeException {
		
		int reportSize  = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			reportSize = dao.allReportSize(userId);
		} finally {
			session.close();
		}
		
		return reportSize;
	}

	// report detail 가져오기
	@Override
	public Map<String, Object> getDetailReport(Map<String, Object> map) throws RuntimeException {
		
		Map<String, Object> detailReport = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			detailReport = dao.getDetailReport(map);

		} finally {
			session.close();
		}
		
		return detailReport;
	}

	// MAX detail_id 가져오기
	@Override
	public int getMaxDetailId() throws RuntimeException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// report 타겟 리스트 가져오기
	@Override
	public List<Map<String, Object>> getTargetList(Map<String, Object> map) throws RuntimeException {
		
		List<Map<String, Object>> targetList = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			targetList = dao.getTargetList(map);
			
		} finally {
			session.close();
		}
		
		return targetList;
	}
	
	// report 타겟 총 개수
	@Override
	public int allTargetSize(Map<String, Object> map) throws RuntimeException {
		
		int reportSize  = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			reportSize = dao.allTargetSize(map);
		} finally {
			session.close();
		}
		
		return reportSize;
	}

	// 캠페인 결과값 카운트 증가
	@Override
	public void plusResultCnt(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	// SMS 발송할 정보 가져오기
	@Override
	public List<Map<String, Object>> getSMSInfo(int maxDetailId) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	// SMS 발송DB INSERT
	@Override
	public void insertSMS(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	// SMS DETAIL INSERT
	@Override
	public void insertSmsDetail(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	// SMS 푸시 플래그 UPDATE
	@Override
	public void updateSmsFlag(int detailId) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}
	
	// report 성공 리스트 가져오기
	@Override
	public List<Map<String, Object>> getSuccessList(Map<String, Object> map) throws RuntimeException {
		
		List<Map<String, Object>> targetList = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			targetList = dao.getSuccessList(map);
			
		} finally {
			session.close();
		}
		
		return targetList;
	}

	// report 성공 총 개수
	@Override
	public int allSuccessSize(Map<String, Object> map) throws RuntimeException {
		
		int reportSize  = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			reportSize = dao.allSuccessSize(map);
		} finally {
			session.close();
		}
		
		return reportSize;
	}
	
	// report 실패 대상자 가져오기
	@Override
	public List<Map<String, Object>> getFailList(Map<String, Object> map) throws RuntimeException {
		
		List<Map<String, Object>> targetList = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			targetList = dao.getFailList(map);
		} finally {
			session.close();
		}
		
		return targetList;
	}
	
	// report 실패 총 개수
	@Override
	public int allFailSize(Map<String, Object> map) throws RuntimeException {
		
		int reportSize = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			reportSize = dao.allFailSize(map);
		} finally {
			session.close();
		}
		
		return reportSize;
	}
	
	// report 오픈 리스트 가져오기
	@Override
	public List<Map<String, Object>> getOpenList(Map<String, Object> map) throws RuntimeException {
		
		List<Map<String, Object>> targetList = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			targetList = dao.getOpenList(map);
			
		} finally {
			session.close();
		}
		
		return targetList;
	}
	
	// report 오픈 총 개수
	@Override
	public int allOpenSize(Map<String, Object> map) throws RuntimeException {
		
		int reportSize  = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			reportSize = dao.allOpenSize(map);
		} finally {
			session.close();
		}
		
		return reportSize;
	}
	
	// report 미오픈 리스트 가져오기
	@Override
	public List<Map<String, Object>> getNoOpenList(Map<String, Object> map) throws RuntimeException {
		
		List<Map<String, Object>> targetList = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			targetList = dao.getNoOpenList(map);
			
		} finally {
			session.close();
		}
		
		return targetList;
	}
	
	// report 미오픈 총 개수
	@Override
	public int allNoOpenSize(Map<String, Object> map) throws RuntimeException {
		
		int reportSize  = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			reportSize = dao.allNoOpenSize(map);
		} finally {
			session.close();
		}
		
		return reportSize;
	}
	
	// report SMS 성공 대상자 가져오기
	@Override
	public List<Map<String, Object>> getSmsSuccessList(Map<String, Object> map) throws RuntimeException {
		
		List<Map<String, Object>> targetList = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			targetList = dao.getSmsSuccessList(map);
			
		} finally {
			session.close();
		}
		
		return targetList;
	}
	
	// report SMS 성공 대상자 총 개수
	@Override
	public int smsSuccessSize(Map<String, Object> map) throws RuntimeException {
		
		int reportSize  = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			reportSize = dao.smsSuccessSize(map);
			
		} finally {
			session.close();
		}
		
		return reportSize;
	}
	
	// report SMS 실패 대상자 가져오기
	@Override
	public List<Map<String, Object>> getSmsFailList(Map<String, Object> map) throws RuntimeException {
		
		List<Map<String, Object>> targetList = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			targetList = dao.getSmsFailList(map);
			
		} finally {
			session.close();
		}
		
		return targetList;
	}
	
	// report SMS 실패 대상자 총 개수
	@Override
	public int smsFailSize(Map<String, Object> map) throws RuntimeException {
		
		int reportSize  = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			reportSize = dao.smsFailSize(map);
			
		} finally {
			session.close();
		}
		
		return reportSize;
	}
	
	// 로컬 sms_detail MAX MSG_ID 가져오기
	@Override
	public int getMaxMsgId() throws RuntimeException {
		// TODO Auto-generated method stub
		int maxMsgId = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			maxMsgId = dao.getMaxMsgId();
			
		} finally {
			session.close();
		}
		
		return maxMsgId;

	}
	
	// 아이하트 SMS 로그 가져오기
	@Override
	public List<Map<String, Object>> getSmsLogList(int maxMsgId) throws RuntimeException {
		// TODO Auto-generated method stub

		return null;
	}
	
	// 로컬 sms_detail 로그 업데이트
	@Override
	public void updateSmsDetail(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
	}
	
	// 로컬 click_detail_id MAX Click_ID 가져오기
	@Override
	public int getMaxClickId() throws RuntimeException {
		// TODO Auto-generated method stub
		int maxClickId = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			maxClickId = dao.getMaxClickId();
			
		} finally {
			session.close();
		}
		
		return maxClickId;

	}
	
	// PMS CLICK 로그 가져오기
	@Override
	public List<Map<String, Object>> getClickLogList(int maxMsgId) throws RuntimeException {
		// TODO Auto-generated method stub

		return null;
	}
	
	// 로컬 push_click_detail 로그 인서트
	@Override
	public void insertClickDetail(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
	}

	// sms 결과 카운트 UPDATE
	@Override
	public void plusSmsCnt(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}
	
	// Click 인앱메세지 상세 가져오기
	@Override
	public List<Map<String, Object>> getClickMessageList(int camId) throws RuntimeException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> clickList = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			clickList = dao.getClickMessageList(camId);
			
		} finally {
			session.close();
		}
		
		return clickList;

	}
	
	// Click 팝업 상세 가져오기
	@Override
	public List<Map<String, Object>> getClickPopupList(int camId) throws RuntimeException {
		// TODO Auto-generated method stub
		List<Map<String, Object>> clickList = null;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			clickList = dao.getClickPopupList(camId);
			
		} finally {
			session.close();
		}
		
		return clickList;

	}
	
	// CLICK IMG URL INSERT
	@Override
	public void insertImgDetail(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			dao.insertImgDetail(map);
			
		} finally {
			session.close();
		}
	}

}
