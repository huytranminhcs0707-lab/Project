package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	BuildingRepository buildingRepository;

	@Override
	public List<BuildingDTO> buildingEntities(String name, Long districtId,List<String> renttype) {
		// TODO Auto-generated method stub
	
		List<BuildingEntity> building = buildingRepository.buildingEntities(name,districtId, renttype);
		List<BuildingDTO> result = new ArrayList<>();
		for (BuildingEntity item : building) {
			BuildingDTO tmp = new BuildingDTO();
			tmp.setName(item.getName());
			tmp.setAddress("đường " + item.getStreet() + " phường " +item.getWard());
			tmp.setNumberofbase(item.getNumberofbase());
			result.add(tmp);
		}
		return result;
	}


}


	
	
	

