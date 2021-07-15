<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.UserVo" %> 

<%
	UserVo authuser = (UserVo)session.getAttribute("authUser");	//이 값을 꺼내오셈.  형변환 해줘야함
	System.out.println(authuser);
%>

		<div id="header" class="clearfix">
			<h1>
				<a href="/mysite/main">MySite</a>
			</h1>

			<% if(authuser != null){	//로그인 성공이라면 %>
				<ul>
					<li><%=authuser.getName() %> 님 안녕하세요^^</li>
					<li><a href="/mysite/user?action=logout" class="btn_s">로그아웃</a></li>
					<li><a href="" class="btn_s">회원정보수정</a></li>
				</ul>			
			<% } else { %>
					<ul>
						<li><a href="/mysite/user?action=loginForm" class="btn_s">로그인</a></li>
						<li><a href="/mysite/user?action=joinForm" class="btn_s">회원가입</a></li>
					</ul>
			<%} %>
				
		</div>
		<!-- //header -->

		<div id="nav">
			<ul class="clearfix">
				<li><a href="">입사지원서</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="/mysite/guest?action=addList">방명록</a></li>
			</ul>
		</div>
		<!-- //nav -->

