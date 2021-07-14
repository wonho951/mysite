package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;

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
			//회원가입 --> 을 저장해야함.			
		}
		
		
		

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
