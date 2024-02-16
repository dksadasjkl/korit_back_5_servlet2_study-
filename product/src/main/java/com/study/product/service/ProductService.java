package com.study.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.study.product.dao.ProductDao;
import com.study.product.dto.InsertProductReqDto;
import com.study.product.dto.InsertProductRsepDto;
import com.study.product.dto.SearchProductRespDto;
import com.study.product.vo.ProductVo;

public class ProductService {
	private static ProductService instance;
	private ProductDao productDao;
	
	private ProductService() {
		productDao = ProductDao.getInstance();
	}
	
	public static ProductService getInstance() {
		if(instance == null) {
			instance = new ProductService();
		}
		return instance;
	}
	
	public boolean isDuplicatedProductName(String productName) {
	
		return productDao.findProductName(productName) != null;
	}
	
	public InsertProductRsepDto addProduct(InsertProductReqDto insertProductReqDto) {
		ProductVo productVo = insertProductReqDto.toVo(); 
		
		int successCount = productDao.savaProduct(productVo); 
		
		return productVo.toInsertDto(successCount);
		
	}
	
	public List<SearchProductRespDto> searchProducts() {
		List<SearchProductRespDto> searchProductRespDtos = new ArrayList<>();
		List<ProductVo> productVos = productDao.getProductLsit();
		
		for(ProductVo prv : productVos) {
			searchProductRespDtos.add(prv.toSearchDto());
		}
		
		return searchProductRespDtos;
		
		
		
//		return productDao.getProductLsit().stream() -> 배열
//				.map(vo -> vo.toSearchDto())	-> map으로 새로운 Dto객체를 배열에 담는다
//				.collect(Collectors.toList());	-> 스트림타입 배열을 List배열로 변환
		
		
		
		// [1, 2, 3] -> [2, 4, 6]
		// let nums [1, 2, 3];
		// nums.map(num => num * 2); 새로운 배열 [2, 4, 6]
		
//		return productDao.getProductLsit().stream()
//				.map(ProductVo::toSearchDto) //ProductVo 클래스에서 toSearchDto객체를 배열에 담기
//				.collect(Collectors.toList());
	}
}
