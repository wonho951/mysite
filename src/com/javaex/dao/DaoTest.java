package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {
		
		
		UserVo userVo = new UserVo("wonho", "123", "최원호", "남");
		
		
		UserDao userDao = new UserDao();		
		System.out.println(userDao.userInsert(userVo));

	}

}
