package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.Utils.NumberUtil;
import com.javaweb.Utils.StringUtil;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImp implements BuildingRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS ="123456";
	public static void joinTable(Map <String, Object> params, List<String> typecode, StringBuilder sql) {
		String staffid = (String) params.get("staffid");
		if (StringUtil.checkString(staffid)) {
			sql.append(" INNER JOIN assignmentbuilding ON assignmentbuilding.buildingid = b.id ");
		}
		if (typecode != null && typecode.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype ON buildingrenttype.buildingid = b.id ");
			sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
		}
	
	}
	public static void queryNormal(Map <String, Object> params, StringBuilder where) {
		for (Map.Entry<String, Object> item : params.entrySet()) {
			if (!item.getKey().equals("staffid") && !item.getKey().equals("typecode") && !item.getKey().startsWith("area") && !item.getKey().startsWith("rentprice")) {
				String value = (String) item.getValue();
				if (StringUtil.checkString(value)) {
					if (NumberUtil.checkNumber(value)) {
						where.append(" AND b." + item.getKey() + " = " + value + " ");
					}
					else {
						where.append(" AND b." + item.getKey() + " like '%" + value + "%' ");
					}
				}
			}
			
		}
	}
	public static void querySpecial(Map <String, Object> params, List<String> typecode, StringBuilder where) {
		String staffid = (String) params.get("staffid");
		if (StringUtil.checkString(staffid)) {
			where.append(" AND assignmentbuilding.staffid = " + staffid );
		}
		String areafrom =(String)  params.get("areafrom");
		String areato = (String) params.get("areato");
		if (StringUtil.checkString(areato) || StringUtil.checkString(areafrom)) {
			where.append(" AND EXISTS (Select * from rentarea WHERE b.id = rentarea.buildingid ");
			if(StringUtil.checkString(areato)) {
				where.append(" AND rentarea.value <= " + areato);
			}
			if(StringUtil.checkString(areafrom)) {
				where.append(" AND rentarea.value >= " + areafrom);
			}
			where.append(") ");
		}
		String rentpricefrom = (String) params.get("rentpricefrom");
		String rentpriceto = (String) params.get("rentpriceto");
		if (StringUtil.checkString(rentpriceto) || StringUtil.checkString(rentpricefrom)) {
			if(StringUtil.checkString(rentpriceto)) {
				where.append(" AND b.rentprice <= " + rentpriceto);
			}
			if(StringUtil.checkString(rentpricefrom)) {
				where.append(" AND b.rentprice >= " + rentpricefrom);
			}
		}
		if (typecode != null && typecode.size() != 0) {
			where.append(" AND( ");
			String sql = typecode.stream().map(it -> " renttype.code like '%" + it + "%' ").collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(" ) ");
		}
		
		
	}
	@Override
	public List<BuildingEntity> find(Map <String, Object> params, List<String> typecode) {
			List<BuildingEntity> result = new ArrayList<>();
			StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.street, b.ward, b.districtid, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee ");
		    sql.append(" FROM building b ");
		    StringBuilder where = new StringBuilder("WHERE 1 = 1 ");
		    joinTable(params, typecode, sql);
		    queryNormal(params, where);
		    querySpecial(params,typecode, where);
		    where.append(" GROUP BY b.id ");
		    sql.append(where);

			try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());){
				while(rs.next()) {
					BuildingEntity building = new BuildingEntity();
					building.setId(rs.getInt("id"));
					building.setName(rs.getString("name"));
					building.setStreet(rs.getString("street"));
					building.setNumberofbasement(rs.getInt("numberofbasement"));
					building.setWard(rs.getString("ward"));
					building.setDistricid(rs.getInt("districtid"));
					building.setFloorarea(rs.getInt("floorarea"));
					building.setRentprice(rs.getInt("rentprice"));
					building.setManagername(rs.getString("managername"));
					building.setManagerphonenumber(rs.getString("managerphonenumber"));
					building.setBrokeragefee(rs.getString("brokeragefee"));
					building.setServicefee(rs.getString("servicefee"));
					result.add(building);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
				
			}
		return result;
	}
 
}
