package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {
		
		
		/* 2021.07.15 test
		UserVo userVo = new UserVo("wonho", "123", "최원호", "남");
		
		
		UserDao userDao = new UserDao();		
		System.out.println(userDao.userInsert(userVo));
		*/
		
		
		//2021.07.15 세션 test
		UserDao userDao = new UserDao();
		UserVo userVo = userDao.getUser("wonho", "123");
		System.out.println(userVo);
		
	}

}
