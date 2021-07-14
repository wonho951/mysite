package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("[UserController]");
		
		String action = request.getParameter("action");
		System.out.println(action);	//확인용
		
		
		if("joinForm".equals(action)) {

			System.out.println("[UserController.joinForm]");
			
			//회원가입 폼 포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinForm.jsp");
			
		} else if ("join".equals(action)){
			System.out.println("[UserController.join]");
			
			
			//회원가입 	
			
			//파라미터 꺼내기
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			System.out.println(id + ", " + pw + ", " + name  + ", " + gender);	//값이 잘 들어가는지 확인용
			
			//vo만들기--> 한덩어리로 만든다
			UserVo userVo = new UserVo(id, pw, name, gender);
			
			//dao.insert(vo); -->db에 저장
			
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/join.jsp");
		}
		
		
		

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
