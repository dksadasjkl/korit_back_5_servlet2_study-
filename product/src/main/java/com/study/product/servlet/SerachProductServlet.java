package com.study.product.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.product.dao.ProductDao;
import com.study.product.entity.Product;

@WebServlet("/products")
public class SerachProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SerachProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("조회 요청 들어옴");
		
		ProductDao productDao = ProductDao.getInstance();
		List<Product> products = productDao.getList();
		
		Map<String, Object> map = new HashMap<>();
		map.put("data", products);
		Gson gson = new Gson();
		
		response.setStatus(200);
		response.setContentType("application/json");
		response.getWriter().println(gson.toJson(map));
		
		
	}

}
