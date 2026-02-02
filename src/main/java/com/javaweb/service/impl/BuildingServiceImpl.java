package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	BuildingRepository buildingRepository;
	@Autowired
	DistrictRepository districtRepository;

	@Override
	public List<BuildingDTO> find(Map <String, Object> params, List<String> typecode) {
		// TODO Auto-generated method stub
	
		List<BuildingEntity> building = buildingRepository.find(params, typecode);
		List<BuildingDTO> result = new ArrayList<>();
		for (BuildingEntity item : building) {
			BuildingDTO tmp = new BuildingDTO();
			tmp.setName(item.getName());
			tmp.setAddress( "Đường " + item.getStreet() + ", " +item.getWard() +", " + districtRepository.findNamebyId(item.getDistricid()).getName());
			tmp.setNumberofbase(item.getNumberofbasement());
			tmp.setManagername(item.getManagername());
			tmp.setManagerphonenumber(item.getManagerphonenumber());
			tmp.setFloorarea(item.getFloorarea());
			tmp.setRentprice(item.getRentprice());
			tmp.setBrokeragefee(item.getBrokeragefee());
			tmp.setServicefee(item.getServicefee());
			result.add(tmp);
		}
		return result;
	}


}


	
	
	

