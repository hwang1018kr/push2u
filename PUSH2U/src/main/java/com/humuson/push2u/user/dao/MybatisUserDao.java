package com.humuson.push2u.user.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.humuson.push2u.user.controller.UserController;
import com.humuson.push2u.user.domain.User;

@Repository("userDao")
public class MybatisUserDao implements UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// MariaDB
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	// MySQL
	@Autowired
	private SqlSessionFactory sqlSessionFactory2;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public void setSqlSessionFactory2(SqlSessionFactory sqlSessionFactory2) {
		this.sqlSessionFactory2 = sqlSessionFactory2;
	}

	//로그인
	@Override
	public User login(Map<String, String> users) throws RuntimeException {
		
		User user = null;
		
		SqlSession session = null;
		
		try{
			
			session = sqlSessionFactory.openSession();
			UserDao dao = session.getMapper(UserDao.class);
			
			logger.debug( "@@@@@@@@@@@@@@@@@@@ cnt : " + String.valueOf(findUserById(users.get("user_id"))) );
			
			if(findUserById(users.get("user_id")) == 1) {
				
				user = dao.login(users);
				
			} else {
				
				return null;
			}
			
		}finally{
			session.close();
		}
		
		return user;
	}
	
	// 아이디로 회원 찾기
	@Override
	public int findUserById(String userId) throws RuntimeException {
		
		int i = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			UserDao dao = session.getMapper(UserDao.class);
			
			i = dao.findUserById(userId);
			
		} finally {
			session.close();
		}
		
		return i;
	}
	
	// 인증문자 INSERT
	@Override
	public void authPhoneNum(Map<String, String> auth) throws RuntimeException {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory2.openSession();
			UserDao dao = session.getMapper(UserDao.class);
			
			dao.authPhoneNum(auth);
		} finally {
			session.close();
		}
	}
	
	// 회원가입
	@Override
	public void join(Map<String, String> joinMap) throws RuntimeException {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			UserDao dao = session.getMapper(UserDao.class);
			
			dao.join(joinMap);
			
		} finally {
			session.close();
		}
		
	}
	
	// custID 체크
	@Override
	public int checkCust(String custId) throws RuntimeException {
		
		int check = 0;
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			UserDao dao = session.getMapper(UserDao.class);
			
			dao.checkCust(custId);
		} finally {
			session.close();
		}
		
		return check;
	}
	
	// App User update
	@Override
	public void updateAppUser(Map<String, String> appUserMap) throws RuntimeException {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			UserDao dao = session.getMapper(UserDao.class);
			
			dao.updateAppUser(appUserMap);
			
		} finally {
			session.close();
		}
		
	}

	// App User insert
	@Override
	public void insertAppUser(Map<String, String> appUserMap) throws RuntimeException {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			UserDao dao = session.getMapper(UserDao.class);
			
			dao.insertAppUser(appUserMap);
			
		} finally {
			session.close();
		}
	}

}
