package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post에서 한글깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		//파라미터의 action값을 읽어옴
		String action = request.getParameter("action");
		
		//미리 올려두면 편함
		BoardDao boardDao = new BoardDao();
		BoardVo boardVo = new BoardVo();
		
		
		if("list".equals(action)) {
			System.out.println("리스트");
			
			List<BoardVo> boardList = boardDao.getBoardList();
			
			
			//어트리뷰트에 담음
			request.setAttribute("boardList", boardList);
			
			WebUtil.forword(request, response, "/WEB-INF/views/board/list.jsp");
			
		} else if ("read".equals(action)) {
			System.out.println("읽기");
			
			//파라미터 꺼내오기
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);
			
			//파라미터 꺼내서 조회했으니 조회수를 올려라
			boardDao.hitUpdate(no);
			
			BoardVo boradVo = boardDao.getBoard(no);
			
			//어트리뷰트에 데이터 넣기
			request.setAttribute("boardRead", boradVo);
			System.out.println(boradVo);
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/views/board/read.jsp");
			
		} else if ("wform".equals(action)) {
			System.out.println("글쓰기폼");
			
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/views/board/writeForm.jsp");
			
		} else if ("write".equals(action)) {
			System.out.println("글쓰기");
			
			//세션 authUser가져옴
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			int userNo = authUser.getNo();
			
			//파라미터꺼내기
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			//미리 올려둔거에 담고
			boardVo = new BoardVo(title, content, userNo);
			
			//등록
			boardDao.boardInsert(boardVo);
			
			//리다이렉트 -> 리스트
			WebUtil.redirect(request, response, "/mysite/board?action=list");
			
		} else if ("boardModify".equals(action)) {
			System.out.println("수정 폼");
			
			//no추출
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);
			//dao에서 게시판 데이터 가져오기
			boardVo = boardDao.getBoard(no);
			System.out.println(boardVo);
			
			
			//데이터 넣기 -- request  어트리뷰트에 데이터를 넣어준다.
			request.setAttribute("bVo", boardVo);
			
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/views/board/modifyForm.jsp");
			
		} else if ("modify".equals(action)) {
			System.out.println("수정하기");
			
			//파라미터 꺼내오기
			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			//vo로 묶음
			boardVo = new BoardVo(no, title, content);
			//System.out.println(boardVo);
			
			//dao에 데이터 업데이트
			boardDao.boardUpdate(boardVo);
			
			//리다이렉트 --> 리스트로 다시 보냄
			WebUtil.redirect(request, response, "/mysite/board?action=list");
			
		} else if("delete".equals(action)) {
			System.out.println("삭제");
	
			//파라미터 꺼내옴
			int no = Integer.parseInt(request.getParameter("no"));
			//System.out.println(no);
			
			//삭제
			boardDao.boardDelete(no);
			
			//리다이렉트
			WebUtil.redirect(request, response, "/mysite/board?action=list");
			
		} else if ("search".equals(action)) {
			System.out.println("검색");
			
			String keyword = request.getParameter("keyword");
			
			List<BoardVo> searchList = boardDao.getBoardList(keyword);
			
			System.out.println(searchList);
			
			request.setAttribute("boardList", searchList);
			
			WebUtil.forword(request, response, "/WEB-INF/views/board/list.jsp");
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
