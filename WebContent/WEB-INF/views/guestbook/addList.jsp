<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>

<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">
		
		
		<!-- header(로고 로그인 버튼) nav (메인 상단메뉴) -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>


		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="/mysite/guest" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label>
									</th>
									<td>
										<input id="input-uname" type="text" name="name">
									</td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</th>
									<td>
										<input id="input-pass" type="password" name="password">
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<textarea name="content" cols="72" rows="5"></textarea>
									</td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center">
										<button type="submit">등록</button>
									</td>
								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">

					</form>



				<c:forEach items = "${gList}" var = "guestList">
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>${guestList.no}</td>
							<td>${guestList.name}</td>
							<td>${guestList.regDate}</td>
							<td>
								<a href="/mysite/guest?action=dform&no=${guestList.no}">삭제</a>>
							</td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">${guestList.content}</td>
						</tr>
					</table>
				</c:forEach>
				
				
<%-- 				<% for (int i = 0; i < guestbookList.size(); i++) {%> --%>
<!-- 					<table class="guestRead"> -->
<!-- 						<colgroup> -->
<!-- 							<col style="width: 10%;"> -->
<!-- 							<col style="width: 40%;"> -->
<!-- 							<col style="width: 40%;"> -->
<!-- 							<col style="width: 10%;"> -->
<!-- 						</colgroup> -->
<!-- 						<tr> -->
<%-- 							<td><%= guestbookList.get(i).getNo() %></td> --%>
<%-- 							<td><%= guestbookList.get(i).getName() %></td> --%>
<%-- 							<td><%= guestbookList.get(i).getRegDate() %></td> --%>
<!-- 							<td> -->
<%-- 								<a href="/mysite/guest?action=dform&no=<%= guestbookList.get(i).getNo()%>">삭제</a>> --%>
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<%-- 							<td colspan=4 class="text-left"><%= guestbookList.get(i).getContent() %></td> --%>
<!-- 						</tr> -->
<!-- 					</table> -->
<%-- 				<%} %> --%>
					<!-- //guestRead -->

				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- //footer -->
<%-- 		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include> --%>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->

</body>

</html>