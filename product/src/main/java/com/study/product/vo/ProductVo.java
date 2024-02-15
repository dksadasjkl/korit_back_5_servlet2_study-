package com.study.product.vo;

import com.study.product.dto.InsertProductRsepDto;
import com.study.product.dto.SearchProductRespDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductVo {
	private int productId;
	private String productName;
	private int productPrice;
	private String productSize;
	
	public InsertProductRsepDto toInsertDto(int successCount) {
		return InsertProductRsepDto.builder()
				.successCount(successCount)
				.productId(productId)
				.productName(productName)
				.productPrice(productPrice)
				.productSize(productSize)
				.build();
	}
	
	public SearchProductRespDto toSearchDto() {
		return SearchProductRespDto.builder()
				.productId(productId)
				.productName(productName)
				.productPrice(productPrice)
				.productSize(productSize)
				.build();
	}
}
