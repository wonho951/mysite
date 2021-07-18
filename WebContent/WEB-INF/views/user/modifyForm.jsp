<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ page import="com.javaex.vo.UserVo" %>

<% 
	UserVo authUser = (UserVo)request.getAttribute("authUser");
	System.out.println("정보수정 : " + authUser);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<!-- header(로고 로그인 버튼) nav (메인 상단메뉴) -->
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>회원</h2>
				<ul>
					<li>회원정보</li>
					<li>로그인</li>
					<li>회원가입</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>회원정보</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원정보</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				 <!-- //content-head -->
	
				<div id="user">
					<div id="modifyForm">
						<form action="/mysite/user" method="get">
	
							<!-- 아이디 -->
							<div class="form-group">
								<label class="form-text" for="input-uid">아이디</label> 
								<span class="text-large bold">${authUser.id }></span>
								<input type = "hidden" name = "action" value = "modify">
								<input type = "hidden" name = "no" value = "${authUser.no }">
							</div>
	
							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">패스워드</label> 
								<input type="text" id="input-pass" name="pw" value="${authUser.pw }" placeholder="비밀번호를 입력하세요"	>
																		<!-- 파라미터 값 잘 확인하자 제발좀 -->
							</div>
	
							<!-- 이름 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label> 
								<input type="text" id="input-name" name="name" value="${authUser.name } " placeholder="이름을 입력하세요">
							</div>
	
							<!-- //성별 -->
							<div class="form-group">
								<span class="form-text">성별</span> 
								
								
								<c:if test = "${authUser.gender == 'male' }">	<!-- "male" 이렇게 하면 에러나고 'male' 이렇게하면 에러 안나넹 -->
									<label for="rdo-male">남</label> 
									<input type="radio" id="rdo-male" name="gender" value="male" checked = "checked"> 
									
									<label for="rdo-female">여</label>  
									<input type="radio" id="rdo-female" name="gender" value="female" >
								</c:if>
								
								<c:if test = "${authUser.gender == 'female' }">
									<label for="rdo-male">남</label> 
 									<input type="radio" id="rdo-male" name="gender" value="male" >
									
									
									<label for="rdo-female">여</label> 
									<input type="radio" id="rdo-female" name="gender" value="female" checked = "checked">
								
								</c:if>
								
								
<%-- 								<% if("male".equals(authUser.getGender())){ %> --%>
								
<!-- 									<label for="rdo-male">남</label>  -->
<!-- 									<input type="radio" id="rdo-male" name="gender" value="male" checked = "checked">  -->
									
<!-- 									<label for="rdo-female">여</label>  -->
<!-- 									<input type="radio" id="rdo-female" name="gender" value="female" >  -->
									
<%-- 								<%} else{ %> --%>
								
<!-- 									<label for="rdo-male">남</label>  -->
<!-- 									<input type="radio" id="rdo-male" name="gender" value="male" >  -->
									
<!-- 									<label for="rdo-female">여</label>  -->
<!-- 									<input type="radio" id="rdo-female" name="gender" value="female" checked = "checked"> -->
									 
<%-- 								<%} %> --%>
							</div>
	
							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">회원정보수정</button>
							</div>
							
						</form>
					
					
					</div>
					<!-- //modifyForm -->
				</div>
				<!-- //user -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->

		<!-- //footer -->
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		
	</div>
	<!-- //wrap -->

</body>

</html>