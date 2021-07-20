package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

public class BoardDao {

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

	
	
	//게시판 리스트(검색안할때)
	public List<BoardVo> getBoardList() {
		return getBoardList("");
	}

	// 사람 리스트(검색할때)
	public List<BoardVo> getBoardList(String keword) {
		List<BoardVo> boardList = new ArrayList<BoardVo>();

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " select  board.no, ";
			query += "         users.name, ";
			query += "         board.title, ";
			query += "         board.content, ";
			query += "         board.hit, ";
			query += "		   reg_date, ";
			query += "		   board.user_no ";
			query += " from users, board ";
			query += " where users.no = board.user_no ";
			query += " order by reg_date desc ";

			if (keword != "" || keword == null) {
				pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			} else {
				query += " and board.title like ? ";
				query += " or users.name like  ? ";

				pstmt = conn.prepareStatement(query); // 쿼리로 만들기
				
				pstmt.setString(1, '%' + keword + '%'); // ?(물음표) 중 1번째, 순서중요
				pstmt.setString(2, '%' + keword + '%'); // ?(물음표) 중 2번째, 순서중요

			}

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("reg_date");
				int userNo = rs.getInt("user_no");

				BoardVo boardVo = new BoardVo(no, name, title, content, hit, regDate, userNo);
				boardList.add(boardVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return boardList;

	}
	
	
	
	
	// 게시판 리스트
	/*public List<BoardVo> getBoardList() {

		// 리스트 생성
		List<BoardVo> boardList = new ArrayList<BoardVo>();

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  board.no, ";
			query += "         users.name, ";
			query += "         board.title, ";
			query += "         board.content, ";
			query += "         board.hit, ";
			query += "		   reg_date, ";
			query += "		   board.user_no ";
			query += " from users, board ";
			query += " where users.no = board.user_no ";
			query += " order by reg_date desc ";

			pstmt = conn.prepareStatement(query); // 쿼리문으로 만들기

			rs = pstmt.executeQuery();

			// 4.결과처리

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("reg_date");
				int userNo = rs.getInt("user_no");

				BoardVo boardVo = new BoardVo(no, name, title, content, hit, regDate, userNo);
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		return boardList;

	}*/

	// 게시판 글 조회하기
	public BoardVo getBoard(int no) {

		BoardVo boardVo = null; // 변수만 잡음

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " select  board.no, ";
			query += "		   users.name, ";
			query += "		   board.title, ";
			query += "		   board.content, ";
			query += "		   board.hit,";
			query += "		   reg_date, ";
			query += "		   board.user_no";
			query += " from users, board ";
			query += " where users.no = board.user_no ";
			query += " and board.no = ? ";

			// System.out.println(query);

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, no); // ?(물음표) 중 1번째, 순서중요

			rs = pstmt.executeQuery(); // 쿼리문 실행

			// 4.결과처리
			while (rs.next()) {
				int boardno = rs.getInt("no");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("reg_date");
				int userNo = rs.getInt("user_no");

				boardVo = new BoardVo(boardno, name, title, content, hit, regDate, userNo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return boardVo;
	}

	
	// 조회수 증가
	public int hitUpdate(int no) {

		int count = 0;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " update board ";
			query += " set hit = hit + 1 ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, no); // ?(물음표) 중 1번째, 순서중요

			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			//System.out.println(count + "건 증가");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

	
	// 글 등록
	public int boardInsert(BoardVo boardVo) {

		int count = 0;
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " INSERT INTO board ";
			query += " values (seq_board_no.nextval, ?, ?, 0, sysdate, ?) ";
			// System.out.println(query);

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, boardVo.getTitle()); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, boardVo.getContent()); // ?(물음표) 중 2번째, 순서중요
			pstmt.setInt(3, boardVo.getUserNo()); // ?(물음표) 중 3번째, 순서중요

			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			// System.out.println("[" + count + "건 추가되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}

	
	// 게시글 수정
	public int boardUpdate(BoardVo boardVo) {
		int count = 0;
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " update board ";
			query += " set title = ?, ";
			query += "     content = ? ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, boardVo.getTitle()); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, boardVo.getContent()); // ?(물음표) 중 2번째, 순서중요
			pstmt.setInt(3, boardVo.getNo()); // ?(물음표) 중 3번째, 순서중요

			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			// System.out.println(count + "건 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}
	
	
	// 게시글 삭제
	public int boardDelete(int no) {
		int count = 0;
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " delete from board ";
			query += " where no = ? ";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, no );// ?(물음표) 중 1번째, 순서중요
			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			// System.out.println(count + "건 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

	
	

}
