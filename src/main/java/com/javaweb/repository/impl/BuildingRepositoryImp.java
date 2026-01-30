package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImp implements BuildingRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS ="123456";
	@Override
	public List<BuildingEntity> buildingEntities(String name, Long districtId, List<String> renttype) {
			List<BuildingEntity> result = new ArrayList<>();
			StringBuilder sql = new StringBuilder("SELECT * FROM building b ");
			if (renttype.size() != 0) {
				sql.append(" JOIN buildingrenttype br ON br.buildingid = b.id JOIN renttype r ON r.id = br.renttypeid ");
			}
			sql.append("WHERE 1 =1 ");
			if (name != null && !name.equals("")) {
				sql.append(" AND b.name like '%" + name + "%' ");
			}
			if (districtId != null) {
				sql.append(" AND b.districtid = " + districtId + " ");
			}
			if (renttype.size() != 0) {
				sql.append(" AND r.code IN(");
				for (int i = 0; i < renttype.size(); i++) {
					sql.append("'");
					sql.append(renttype.get(i));
					sql.append("'");
					if (i < renttype.size()-1) {
						sql.append(", ");
					}
				}
				sql.append(") ");
			}
			
			try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());){
				while(rs.next()) {
					BuildingEntity building = new BuildingEntity();
					building.setName(rs.getString("name"));
					building.setStreet(rs.getString("street"));
					building.setNumberofbase(rs.getInt("numberofbasement"));
					building.setWard(rs.getString("ward"));
					result.add(building);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
				
			}
		return result;
	}
 
}
