package com.study.insert_and_select.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
		System.out.println(builder.toString());
		
		Student student = Student.builder()
				.name("서창현")
				.age(27)
				.build();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String studentJson = gson.toJson(student);
		System.out.println(studentJson);
		
	}

}
