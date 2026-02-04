package com.javaweb.repository.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;

@Repository
public class RentAreaRepositoryImp implements RentAreaRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS ="123456";
	@Override
	public List<RentAreaEntity> findValuebyBuildingid(Integer buildingid) {
		StringBuilder sql = new StringBuilder("Select rentarea.value from rentarea where rentarea.buildingid = " + buildingid);
		List<RentAreaEntity> result = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());){
				while(rs.next()) {
					RentAreaEntity tmp = new RentAreaEntity();
					tmp.setValue(rs.getString("value"));
					result.add(tmp);
					
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
				
			}
		return result;
	}


}
