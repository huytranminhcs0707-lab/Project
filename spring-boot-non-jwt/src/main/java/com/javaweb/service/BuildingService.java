package com.javaweb.service;

import java.util.List;

import com.javaweb.model.BuildingDTO;



public interface BuildingService {
	List<BuildingDTO> buildingEntities(String name, Long districtId,List<String> renttype);
}
