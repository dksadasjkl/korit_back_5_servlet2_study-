package com.study.product.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.study.product.dto.InsertProductReqDto;

public class RequestUtil {
	
	public static String getJsonData(HttpServletRequest request) throws IOException {
		String requestJsonData = null;
		StringBuilder builder = new StringBuilder();
		
		String readLineData = null;
		// getReader() 예외처리 -> 톰캣으로 예외미룸
		BufferedReader reader = request.getReader();
		
		while((readLineData = reader.readLine()) != null) {
			builder.append(readLineData);
		}
		requestJsonData = builder.toString();
		
		return requestJsonData;
	}
	
	public static <T> T convertJsonData(HttpServletRequest request, Class<T> classOfT)  throws IOException{
		String requestJsonData = null;
		StringBuilder builder = new StringBuilder();
		
		String readLineData = null;
		BufferedReader reader = request.getReader();
		
		while((readLineData = reader.readLine()) != null) {
			builder.append(readLineData);
		}
		requestJsonData = builder.toString();
		
		Gson gson = new Gson();
		return gson.fromJson(requestJsonData, classOfT);
	}
	
	
}
