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
	
	// report 리스트 가져오기
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
	
	// report 총 개수
	@Override
	public int allTargetSize(int camId) throws RuntimeException {
		
		int reportSize  = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			PushDao dao = session.getMapper(PushDao.class);
			
			reportSize = dao.allTargetSize(camId);
		} finally {
			session.close();
		}
		
		return reportSize;
	}
	
	
}
