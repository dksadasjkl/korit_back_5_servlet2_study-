package com.study.product.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.product.dao.ProductDao;
import com.study.product.entity.Product;

@WebServlet("/product")
public class InsertProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InsertProductServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder = new StringBuilder();
		
		String readData = null;
		BufferedReader reader = request.getReader();
		
		while((readData = reader.readLine()) != null) {
			builder.append(readData);
		}
		
		Gson gson = new Gson();
		Product product = gson.fromJson(builder.toString(), Product.class);
		
		ProductDao productDao = ProductDao.getInstance();
		
		Product findProductName = productDao.findProductName(product.getProductName());
		if (findProductName != null) {
			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("errorMessege", "중복");
			
			response.setStatus(400);
			response.setContentType("application/json");
			response.getWriter().println(gson.toJson(errorMap));
			return;
		}
		
		
		int successCount = productDao.savaProduct(product);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("successCount", successCount);
		
		response.setStatus(200);
		response.setContentType("application/json");
		response.getWriter().println(gson.toJson(responseMap));
		
	}

}
