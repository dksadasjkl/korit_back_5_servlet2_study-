package com.study.insert_and_select.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.study.insert_and_select.dao.StudentDao;
import com.study.insert_and_select.entity.Student;

@WebServlet("/data/addition")
public class DataInsetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DataInsetServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder = new StringBuilder();
				
		String readData = null;
		
		BufferedReader reader = request.getReader();
		
		while((readData = reader.readLine()) != null) {
			builder.append(readData);
		}
		
		// Json -> Map
		
		Gson gson = new Gson();
		
		Map<String, Object> map = gson.fromJson(builder.toString(), Map.class);
		System.out.println(map);
		System.out.println(map.get("name"));
		System.out.println(map.get("age"));
		
		// Json -> Entity객체 
		// -> 변수명과 자료형이 일치해야함
		Student student = gson.fromJson(builder.toString(), Student.class);
		System.out.println(student);
		System.out.println(student.getName());
		System.out.println(student.getAge());
		
		StudentDao studentDao = StudentDao.getInstance();
		
		Student findStudent = studentDao.findStudentByName(student.getName());
		
		// 값이 중복되어졌을때
		if (findStudent != null) {
			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("errorMessage", "이미 등록된 이름입니다.");
			
			response.setStatus(400);
			response.setContentType("application/json");
			response.getWriter().println(gson.toJson(errorMap));
			return;
		}
		
		
		// 외부 클래스 파일을 객체형태로 불러옴
		// 데이터베이스 커넥터 드라이브 클래스 이름
		// mysql-connector -> com.mysql -> cj -> jdbc -> Driver -> quailname
		
		int successCount = studentDao.saveStudent(student);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", 201);
		responseMap.put("data", "응답데이터");
		responseMap.put("successCount", successCount);
		
		response.setStatus(201);
		response.setContentType("application/json");
		
		PrintWriter writer = response.getWriter();
		writer.println(gson.toJson(responseMap));
		
		
		
		
		
		
//		System.out.println(builder.toString());
//		
//		Student student = Student.builder()
//				.name("서창현")
//				.age(27)
//				.build();
//		
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		
//		String studentJson = gson.toJson(student);
//		System.out.println(studentJson); 
		
	}

}
