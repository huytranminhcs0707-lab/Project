package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
@Repository
public class DistrictRepositoryImp implements DistrictRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS ="123456";
	@Override
	public DistrictEntity findNamebyId(Integer districtid) {
		StringBuilder sql = new StringBuilder("Select district.name from district where district.id = " + districtid);
		DistrictEntity result = new DistrictEntity();
		try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());){
				while(rs.next()) {
					result.setName(rs.getString("name"));
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
				
			}
		return result;
	}	
	
}
