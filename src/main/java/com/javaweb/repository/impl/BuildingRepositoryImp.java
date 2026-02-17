package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.Utils.NumberUtil;
import com.javaweb.Utils.StringUtil;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImp implements BuildingRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS ="123456";
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Integer staffid =  buildingSearchBuilder.getStaffid();
		if (staffid != null) {
			sql.append(" INNER JOIN assignmentbuilding ON assignmentbuilding.buildingid = b.id ");
		}
		if (buildingSearchBuilder.getTypecode() != null && buildingSearchBuilder.getTypecode().size() != 0) {
			sql.append(" INNER JOIN buildingrenttype ON buildingrenttype.buildingid = b.id ");
			sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
		}
	
	}
	public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldname = item.getName();
				if (!fieldname.equals("staffid") && !fieldname.equals("typecode") && !fieldname.startsWith("area") && !fieldname.startsWith("rentprice")) {
					Object value = item.get(buildingSearchBuilder);
					if (value != null) {
						if (item.getType().getName().equals("java.lang.Integer") || item.getType().getName().equals("java.lang.Long")) {
							where.append(" AND b." + fieldname + " = " + value + " ");
						}
						else {
							where.append(" AND b." + fieldname + " like '%" + value + "%' ");
						}
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		Integer staffid =  buildingSearchBuilder.getStaffid();
		if (staffid != null) {
			where.append(" AND assignmentbuilding.staffid = " + staffid );
		}
		Integer areafrom =buildingSearchBuilder.getAreafrom();

		Integer areato = buildingSearchBuilder.getAreato();

		if (areato != null || areafrom != null) {
			where.append(" AND EXISTS (Select * from rentarea WHERE b.id = rentarea.buildingid ");
			if(areato != null ) {
				where.append(" AND rentarea.value <= " + areato);
			}
			if(areafrom != null) {
				where.append(" AND rentarea.value >= " + areafrom);
			}
			where.append(") ");
		}
		Integer rentpricefrom = buildingSearchBuilder.getRentpricefrom();
		Integer rentpriceto = buildingSearchBuilder.getRentpriceto();
		if (rentpriceto != null|| rentpricefrom != null) {
			if(rentpriceto != null) {
				where.append(" AND b.rentprice <= " + rentpriceto);
			}
			if(rentpricefrom != null) {
				where.append(" AND b.rentprice >= " + rentpricefrom);
			}
		}
		List<String> typecode = buildingSearchBuilder.getTypecode();
		if (typecode != null && typecode.size() != 0) {
			where.append(" AND( ");
			String sql = typecode.stream().map(it -> " renttype.code like '%" + it + "%' ").collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(" ) ");
		}
		
		
	}
	@Override
	public List<BuildingEntity> find(BuildingSearchBuilder buildingSearchBuilder) {
			List<BuildingEntity> result = new ArrayList<>();
			StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.street, b.ward, b.districtid, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee ");
		    sql.append(" FROM building b ");
		    StringBuilder where = new StringBuilder("WHERE 1 = 1 ");
		    joinTable(buildingSearchBuilder, sql);
		    queryNormal(buildingSearchBuilder, where);
		    querySpecial(buildingSearchBuilder, where);
		    where.append(" GROUP BY b.id ");
		    sql.append(where);
		    System.out.println(where);
		   
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
