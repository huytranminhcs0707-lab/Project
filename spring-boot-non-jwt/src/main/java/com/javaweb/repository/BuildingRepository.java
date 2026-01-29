package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.BuildingEntity;



public interface BuildingRepository {
	List<BuildingEntity> buildingEntities(String name, Long districtId,List<String> renttype);
	
}
