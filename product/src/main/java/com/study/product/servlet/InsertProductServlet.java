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
import com.study.product.dto.InsertProductReqDto;
import com.study.product.service.ProductService;
import com.study.product.utils.RequestUtil;
import com.study.product.utils.ResponseEntity;
import com.study.product.vo.ProductVo;

@WebServlet("/product")
public class InsertProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
       
   
    public InsertProductServlet() {
        super();
        productService = ProductService.getInstance();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 호출할때 자료형 클래스 타입 지정됨 -> 기본 Object 클래스
		InsertProductReqDto reqDto = RequestUtil.convertJsonData(request, InsertProductReqDto.class);
		if (productService.isDuplicatedProductName(reqDto.getProductName())) {
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("errorMessage", "이미 등록된 상품명");
			
			ResponseEntity.ofJson(response, 400, responseMap);
			return;
		}
		
		ResponseEntity.ofJson(response, 201, productService.addProduct(reqDto));
		 
		
		
//		ProductDao productDao = ProductDao.getInstance();
//		
//		Product findProductName = productDao.findProductName(product.getProductName());
//		if (findProductName != null) {
//			Map<String, Object> errorMap = new HashMap<>();
//			errorMap.put("errorMessege", "중복");
//			
//			response.setStatus(400);
//			response.setContentType("application/json");
//			response.getWriter().println(gson.toJson(errorMap));
//			return;
//		}
//		
//		
//		int successCount = productDao.savaProduct(product);
//		Map<String, Object> responseMap = new HashMap<>();
//		responseMap.put("successCount", successCount);
//		
//		response.setStatus(200);
//		response.setContentType("application/json");
//		response.getWriter().println(gson.toJson(responseMap));
//	
	}

}
