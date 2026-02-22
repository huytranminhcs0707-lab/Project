package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;

@Transactional
@Repository

public class BuildingRepositoryImpl implements BuildingRepositoryCustom{
	@Autowired
	private ModelMapper modelMapper;

	
	@PersistenceContext
	private EntityManager entityManager;
	

	
	
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
	public void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
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
	
	public void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
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
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("SELECT b.* ");
	    sql.append(" FROM building b ");
	    StringBuilder where = new StringBuilder("WHERE 1 = 1 ");
	    joinTable(buildingSearchBuilder, sql);
	    queryNormal(buildingSearchBuilder, where);
	    System.out.println(where);
	    querySpecial(buildingSearchBuilder, where);
	    where.append(" GROUP BY b.id ");
	    sql.append(where);
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
	}
	@Override
	public void createNewBuilding(BuildingRequestDTO buildingRequestDTO) {
		 BuildingEntity buildingEntity = modelMapper.map(buildingRequestDTO, BuildingEntity.class);
		    DistrictEntity district = new DistrictEntity();
		    district.setId(buildingRequestDTO.getDistrictid());
		    buildingEntity.setDistrict(district);
		if (buildingRequestDTO.getId() != null) {
			 entityManager.merge(buildingEntity);
		}
		else {
		    entityManager.persist(buildingEntity);
		}
	    	
	}
	@Override
	public void deleteBuilding(Integer id) {
		// TODO Auto-generated method stub
		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
		entityManager.remove(buildingEntity);
		
	}
}

