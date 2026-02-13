package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	BuildingRepository buildingRepository;
	@Autowired
	BuildingDTOConverter buildingDTOConverter;
	@Autowired
	BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	@Override
	public List<BuildingDTO> find(Map <String, Object> params, List<String> typecode) {
		// TODO Auto-generated method stub
		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typecode);
		List<BuildingEntity> building = buildingRepository.find(buildingSearchBuilder);
		List<BuildingDTO> result = new ArrayList<>();
		for (BuildingEntity item : building) {
			BuildingDTO tmp = buildingDTOConverter.toBuildingDTO(item);
			result.add(tmp);
		}
		return result;
	}
	@Override
	public void createNewBuilding(BuildingRequestDTO buildingRequestDTO) {
		// TODO Auto-generated method stub
		buildingRepository.createNewBuilding(buildingRequestDTO);
		
	}
	@Override
	public void deleteBuilding(Integer id) {
		// TODO Auto-generated method stub
		buildingRepository.deleteBuilding(id);
		
	}


}


	
	
	

