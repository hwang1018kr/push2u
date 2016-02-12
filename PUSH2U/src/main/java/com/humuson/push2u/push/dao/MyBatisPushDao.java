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
import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

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

	
	
}
