package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;


public class GuestbookDao {
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public void close() {
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

	
	
	//리스트 가져오기
	public List<GuestbookVo> getguestList() {
		// 리스트 생성
		List<GuestbookVo> guestList = new ArrayList<GuestbookVo>();

		this.getConnection();

		
		
		try {

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  no, ";
			query += "         name, ";
			query += "         password, ";
			query += "         content, ";
			query += "         reg_date ";
			query += " from guestbook ";
			query += " order by no desc ";	
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
		    // 4.결과처리
			
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");

				GuestbookVo gusetVo = new GuestbookVo(no, name, password, content, regDate);
				guestList.add(gusetVo);
			}
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}

		this.close();
		
		return guestList;
	}
	
	
	// 등록
	public int guestInsert(GuestbookVo gusetVo) {
		int count = 0;
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " INSERT INTO guestbook ";
			query += " VALUES (SEQ_GUESTBOOK_NO.nextval, ?, ?, ?, sysdate) ";
			// System.out.println(query);

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, gusetVo.getName()); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, gusetVo.getPassword()); // ?(물음표) 중 2번째, 순서중요
			pstmt.setString(3, gusetVo.getContent()); // ?(물음표) 중 3번째, 순서중요

			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			// System.out.println("[" + count + "건 추가되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}

	
	// 삭제
	public int guestDelete(GuestbookVo guestVo) {
		int count = 0;
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " delete from guestbook ";
			query += " where no = ? ";
			query += " and password = ? ";
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, guestVo.getNo());// ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, guestVo.getPassword());
			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			// System.out.println(count + "건 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}
	
	//변수명 사용시 같은 이름 사용도 할 수 있고, 그게 가장 좋은 방법이면 사용하는게 좋다.

}
