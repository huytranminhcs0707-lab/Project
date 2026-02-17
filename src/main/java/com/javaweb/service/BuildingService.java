package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;



public interface BuildingService {
	List<BuildingDTO> find(Map <String, Object> params, List<String> typecode);
	void changeBuilding(BuildingRequestDTO buildingRequestDTO);
	void createNewBuilding(BuildingRequestDTO buildingRequestDTO);
	void deleteBuilding(Integer id);
}
