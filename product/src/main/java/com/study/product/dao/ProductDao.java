package com.study.product.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;
import com.study.product.config.DBConfig;
import com.study.product.config.DBConnectionMgr;
import com.study.product.vo.ProductVo;

public class ProductDao {
	private static ProductDao instance;
	private DBConnectionMgr pool;
	
	private ProductDao() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}
	
	public ProductVo findProductName(String productName) {
		ProductVo productVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "select * from product_tb where product_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				productVo = ProductVo.builder()
						.productId(rs.getInt(1))
						.productName(rs.getString(2))
						.productPrice(rs.getInt(3))
						.productSize(rs.getString(4))
						.build();
			}
 		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return productVo;
	}
	
	
	
	public int savaProduct(ProductVo productVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int successCount = 0;
		ResultSet rs = null;
		
		try {
				con = pool.getConnection();
				String sql = "insert into product_tb values(0, ?, ?, ?)";
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //키값 가져옴
				pstmt.setString(1, productVo.getProductName());
				pstmt.setInt(2, productVo.getProductPrice());
				pstmt.setString(3, productVo.getProductSize());
				
				successCount = pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys(); //해당 키값 전부
				if(rs.next()) {
					productVo.setProductId(rs.getInt(1)); // 순서대로 VoId에 넣는다
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		
		return successCount;
	}
	
	
	public List<ProductVo> getProductLsit() {
		List<ProductVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = pool.getConnection();
			String sql = "select * from product_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVo productVo = ProductVo.builder()
						.productId(rs.getInt(1))
						.productName(rs.getString(2))
						.productPrice(rs.getInt(3))
						.productSize(rs.getString(4))
						.build();
				list.add(productVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}	
		return list;
	}
}



