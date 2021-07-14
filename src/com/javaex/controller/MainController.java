package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;


@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;	//아직은 몰라도 되는거. 중복 안되게 시리얼번호 주는거 라는 느낌으로 말씀해주셨음.
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("[MainController]");
		
		//포워드
		WebUtil.forword(request, response, "/WEB-INF/views/main/index.jsp");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
