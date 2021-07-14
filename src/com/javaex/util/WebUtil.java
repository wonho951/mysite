package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {

	
	//필드
	
	//생성자
	
	//메소드 g/s
	
	//메소드-일반	
	//forword
	
	public static void forword(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		
		//forword 자주 사용하니 static으로 올려두고 사용
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

	
	//redirect
	public static void redirect (HttpServletRequest request, HttpServletResponse response, String url) throws IOException{
		
		//redirect 자주 사용하니 static으로 올려두고 사용
		response.sendRedirect(url);
	}
	
	
}
