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
	
	// �ֱ� Ǫ�� ��� ��������
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
	
	// Ǫ�� ���ø� ��������
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

	// App User ��� ��������
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
	
	// MAX ķ���� ���̵� ��������
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
	
	// MAX Ǫ�� ���̵� ��������
	@Override
	public int getMaxPushId() throws RuntimeException {
		// TODO Auto-generated method stub
		return 0;
	}

	// ķ���� ���� insert
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

	// ķ���� ������ insert
	@Override
	public void insertPushDetail(Map<String, Object> map) throws RuntimeException {
		
	}

	// Ǫ�� �α� ��������
	@Override
	public List<Map<String, Object>> getPushDetailList(int maxPushId) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	// ���� DB�� Ǫ�� �α� INSERT
	@Override
	public void insertPushLog(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}
	
	
	// report ����Ʈ ��������
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
	
	// report �� ����
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

	// report detail ��������
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

	// MAX detail_id ��������
	@Override
	public int getMaxDetailId() throws RuntimeException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// report Ÿ�� ����Ʈ ��������
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
	
	// report Ÿ�� �� ����
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

	// ķ���� ����� ī��Ʈ ����
	@Override
	public void plusResultCnt(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	// SMS �߼��� ���� ��������
	@Override
	public List<Map<String, Object>> getSMSInfo(int maxDetailId) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	// SMS �߼�DB INSERT
	@Override
	public void insertSMS(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	// SMS DETAIL INSERT
	@Override
	public void insertSmsDetail(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	// SMS Ǫ�� �÷��� UPDATE
	@Override
	public void updateSmsFlag(int detailId) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}
	
	// report ���� ����Ʈ ��������
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

	// report ���� �� ����
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
	
	// report ���� ����� ��������
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
	
	// report ���� �� ����
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
	
	// report ���� ����Ʈ ��������
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
	
	// report ���� �� ����
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
	
	// report �̿��� ����Ʈ ��������
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
	
	// report �̿��� �� ����
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
	
	// report SMS ���� ����� ��������
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
	
	// report SMS ���� ����� �� ����
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
	
	// report SMS ���� ����� ��������
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
	
	// report SMS ���� ����� �� ����
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
	
	// ���� sms_detail MAX MSG_ID ��������
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
	
	// ������Ʈ SMS �α� ��������
	@Override
	public List<Map<String, Object>> getSmsLogList(int maxMsgId) throws RuntimeException {
		// TODO Auto-generated method stub

		return null;
	}
	
	// ���� sms_detail �α� ������Ʈ
	@Override
	public void updateSmsDetail(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
	}
	
	// ���� click_detail_id MAX Click_ID ��������
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
	
	// PMS CLICK �α� ��������
	@Override
	public List<Map<String, Object>> getClickLogList(int maxMsgId) throws RuntimeException {
		// TODO Auto-generated method stub

		return null;
	}
	
	// ���� push_click_detail �α� �μ�Ʈ
	@Override
	public void insertClickDetail(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
	}

	// sms ��� ī��Ʈ UPDATE
	@Override
	public void plusSmsCnt(Map<String, Object> map) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}
	
	// Click �ξ۸޼��� �� ��������
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
	
	// Click �˾� �� ��������
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
