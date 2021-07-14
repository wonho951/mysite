package com.javaex.dao;

import com.javaex.vo.UserVo;

public class UserDao {

	//필드
	
	//생성자
	
	//메소드 g/s
	
	//메소드 - 일반
	
	public int userInsert(UserVo userVo) {
		
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩

		    // 2. Connection 얻어오기

		    // 3. SQL문 준비 / 바인딩 / 실행
		    
		    // 4.결과처리

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}

		
		return 1;
	}
	
}
