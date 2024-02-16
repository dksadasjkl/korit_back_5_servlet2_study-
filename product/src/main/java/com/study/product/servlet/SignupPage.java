package com.study.product.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signup.do")
public class SignupPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignupPage() {
        super();
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext application = request.getServletContext(); // 전역
		// key, Object 객체
		application.setAttribute("key1", "value1"); 
		application.setAttribute("key2", "value2"); 
		application.setAttribute("key4", "value3"); 
		
		
		request.setAttribute("key5", "value4"); // 요청때마다 객체세팅 (개인 저장)
		request.setAttribute("key5", "value5"); 
		request.setAttribute("key6", "value6");
		
		HttpSession session = request.getSession(); //고유한 sessionId (서버에 개인 저장)
		session.setMaxInactiveInterval(1000 * 60 * 60);
		session.setAttribute("key4", "value7"); 
		session.setAttribute("key8", "value8"); 
		session.setAttribute("key9", "value9");
		
		request.getRequestDispatcher("WEB-INF/views/signup.jsp").forward(request, response); //jsp로 객체 전달 
	}


}
