package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/guest")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//파라미터의 action값을 읽어옴.
		String action = request.getParameter("action");
		//System.out.println(action); //확인용
		
		GuestbookDao guestDao = new GuestbookDao();	//미리 올려줌
		
		if ("addList".equals(action)) {
			System.out.println("[리스트]");
			
			List<GuestbookVo> guestbookList = guestDao.getguestList();
			
			/*
			System.out.println("controller-------------------------");
			System.out.println(guestbookList);
			*/
			
			request.setAttribute("gList", guestbookList);
			
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/addList.jsp");
						
			
		} else if ("add".equals(action)) {
			System.out.println("등록");
			
			//파라미터꺼내기
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			//vo만들기
			GuestbookVo guestVo = new GuestbookVo(name, password, content);
			
			//dao에 저장
			guestDao.guestInsert(guestVo);
			
			//리다이렉트
			WebUtil.redirect(request, response, "/mysite/guest?action=addList");
			
		} else if ("dform".equals(action)) {
			System.out.println("삭제폼");
						
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/deleteForm.jsp");
			
		} else if ("delete".equals(action)) {
			System.out.println("삭제");
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestbookVo guestbookVo = new GuestbookVo(no, password);
			
			guestDao.guestDelete(guestbookVo);
			
			WebUtil.redirect(request, response, "/mysite/guest?action=addList");
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
