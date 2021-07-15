package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("[UserController]");
		
		//텍스트 인코딩 --> 한글깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		
		//파라미터 가져오기(업무에 해당하는 action)
		String action = request.getParameter("action");
		System.out.println(action);	//확인용
		
		
		
		if("joinForm".equals(action)) {	//회원가입 폼

			System.out.println("[UserController.joinForm]");
			
			//회원가입 폼 포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinForm.jsp");
			
		} else if ("join".equals(action)){	//회원가입
			System.out.println("[UserController.join]");
			
			//파라미터 꺼내기
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			//System.out.println(id + ", " + pw + ", " + name  + ", " + gender);	//값이 잘 들어가는지 확인용
			
			
			//vo만들기--> 한덩어리로 만든다
			UserVo userVo = new UserVo(id, pw, name, gender);
			System.out.println(userVo);
			
			
			//dao.insert(vo); -->db에 저장
			UserDao userDao = new UserDao();
			int count = userDao.userInsert(userVo);
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinOk.jsp");
			
		}else if ("loginForm".equals(action)) {			
			System.out.println("[UserController.loginForm]");
			
			//로그인폼 포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/loginForm.jsp");
			
		} else if("login".equals(action)) {
			System.out.println("[UserController.login]");
			
			//파라미터에 값 꺼내기
			String id = request.getParameter("id");
			String password = request.getParameter("pw");

			
			//dao 회원정보 조회하기(세션 저장용)
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(id, password);
			
			
			//성공일때(아이디 비번 일치했을때) 세션에 저장	--> 실패일때도 작성해야함.
			HttpSession session = request.getSession();	//내놔 라고 요청함
			session.setAttribute("authUser", userVo);	//authUser 는 별명.
			
			//리다이렉트
			WebUtil.redirect(request, response, "/mysite/main");
		}
		
		
		

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
