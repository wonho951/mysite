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
		
		
		//System.out.println("[UserController]");
		
		//텍스트 인코딩 --> 한글깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		
		//파라미터 가져오기(업무에 해당하는 action)
		String action = request.getParameter("action");
		System.out.println(action);	//확인용
		
		
		
		if("joinForm".equals(action)) {	//회원가입 폼

			//System.out.println("[UserController.joinForm]");
			
			//회원가입 폼 포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinForm.jsp");
			
		} else if ("join".equals(action)){	//회원가입
			//System.out.println("[UserController.join]");
			
			//파라미터 꺼내기
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			//System.out.println(id + ", " + pw + ", " + name  + ", " + gender);	//값이 잘 들어가는지 확인용
			
			
			//vo만들기--> 한덩어리로 만든다
			UserVo userVo = new UserVo(id, pw, name, gender);
			//System.out.println(userVo);
			
			
			//dao.insert(vo); -->db에 저장
			UserDao userDao = new UserDao();
			int count = userDao.userInsert(userVo);
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinOk.jsp");
			
		}else if ("loginForm".equals(action)) {			
			//System.out.println("[UserController.loginForm]");
			
			//로그인폼 포워드
			WebUtil.forword(request, response, "/WEB-INF/views/user/loginForm.jsp");
			
		} else if("login".equals(action)) {
			//System.out.println("[UserController.login]");
			
			//파라미터에 값 꺼내기
			String id = request.getParameter("id");
			String password = request.getParameter("pw");

			
			//dao 회원정보 조회하기(세션 저장용)
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(id, password);
			
			
			if(userVo != null) {
				//System.out.println("로그인 성공");
				//성공일때(아이디 비번 일치했을때) 세션에 저장	
				HttpSession session = request.getSession();	//내놔 라고 요청함
				session.setAttribute("authUser", userVo);	//authUser 는 별명. jsp에 데이터 전달할때 비교 --> request.setAttribute();
															// 실제 데이터는 userVo임
				//리다이렉트
				WebUtil.redirect(request, response, "/mysite/main");
				
			} else {	//로그인 실패시
				//System.out.println("로그인 실패");
				
				
				//리다이렉트 --> 로그인폼 페이지
				WebUtil.redirect(request, response, "/mysite/user?action=loginForm&result=fail");
				
				//실패일때 (아이디 비번 불일치) 
				
				
			}		
	
			
		} else if("logout".equals(action)) {
			//System.out.println("[UserController.logout]");
			
			//세션에 있는 "authUser"의 정보 삭제
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");	//지정된 이름에 해당하는 객체를 세션에서 제거한다
			session.invalidate();	//메모리 지우는거
			
			//리다이렉트 --> 로그아웃시 메모리 지우고 다시 메인페이로 보냄
			WebUtil.redirect(request, response, "/mysite/main");
			
		} 
		
		
		else if("modifyForm".equals(action)) {
			System.out.println("[UserController.modifyForm]");
			
			
			//로그인한 사용자의 회원정보를 보여줘야한다. --> 세션에서 가져온다
			HttpSession session = request.getSession();
			
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			int no = authUser.getNo();	//no만 가져오는 이유는 메모리를 최소한으로 사용하기 위해서
			
			//로그인한 사용자 회원정보 가져오기
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUserInfo(no);
			
		
			request.setAttribute("authUser", userVo);
			System.out.println("컨트롤 : "+userVo);
			
			//포워드 하기
			WebUtil.forword(request, response, "/WEB-INF/views/user/modifyForm.jsp");
			
			
			
			/*
			대근이는 바로 포워드만 함.	--> modifyForm에 세션에서 값 가져오고 있음.
			WebUtil.forword(request, response, "/WEB-INF/views/user/modifyForm.jsp");
			
			*/
			
		} else if ("modify".equals(action))	{
			System.out.println("[UserController.modify]");
			
			
			/*선생님 코드*/
			HttpSession session = request.getSession();	//세션 내놔
			
			//vo만들기
			//세션에서 로그인한 사용자의 no를 가져온다.
			//나머지정보는 파라미터로 받는다
			int no = ((UserVo)session.getAttribute("authUser")).getNo();	//--> 세션에서 가온나 해서 가져옴.
			String password = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo(no, password, name, gender);
			
			//db에 업데이트
			UserDao userDao = new UserDao();
			int count = userDao.getUserupdate(userVo);
			
			//세션에 이름정보 업데이트(수정)
			((UserVo)session.getAttribute("authUser")).setName(name);
			
			//리다이렉트
			WebUtil.redirect(request, response, "/mysite/main");
			
			
			
			
			/*현재 로그인한 상태임*/
			
			/*
			UserDao userDao = new UserDao();
			
			//세션에서 no 가져옴
			HttpSession session = request.getSession();	//세션 내놔
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			
			//파라미터값 불러온다
			int no = Integer.parseInt(request.getParameter("no"));	//새로 번호 부여하는건가?
			String password = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			System.out.println(password + name + gender);	//확인용
			
			
			//vo에 파라미터 값 저장.
			UserVo userVo = new UserVo(no, password, name, gender);
			//System.out.println(userVo);	//확인용

			//update ㄱㄱ
			userDao.getUserupdate(userVo);	
			
			
			authUser.setName(name);
			
			session.setAttribute("authUser", userVo);	//세션값 변경함
			
			
			//메인으로 리다이렉트
			WebUtil.redirect(request, response, "/mysite/main");
			*/
			
			
			

			
		} 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//파라미터 = 데이터 있는곳
	//세션 안에 어트리뷰트와 리퀘스트 안에 어트리뷰트는 다름.
	//session. 하는 순간 그 세션의 값만 가져옴. 다른 세션에 접근 불가.
}
