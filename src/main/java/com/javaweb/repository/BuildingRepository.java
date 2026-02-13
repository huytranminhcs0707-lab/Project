package com.javaweb.repository;

import java.util.List;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.entity.BuildingEntity;



public interface BuildingRepository {
	List<BuildingEntity> find(BuildingSearchBuilder buildingSearchBuilder);
	void createNewBuilding(BuildingRequestDTO buldingRequestDTO);
	void deleteBuilding(Integer id);
}
