package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	RentAreaRepository rentareaRepository;
	@Autowired
	ModelMapper modelMapper;
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO tmp = modelMapper.map(item, BuildingDTO.class);
		List<RentAreaEntity> rentarea = rentareaRepository.findValuebyBuildingid(item.getId());
		String rentAreaResult = rentarea.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		tmp.setAddress( "Đường " + item.getStreet() + ", " +item.getWard() +", " + districtRepository.findNamebyId(item.getDistricid()).getName());
		tmp.setRentarea(rentAreaResult);
//		tmp.setName(item.getName());
//		tmp.setNumberofbase(item.getNumberofbasement());
//		tmp.setManagername(item.getManagername());
//		tmp.setManagerphonenumber(item.getManagerphonenumber());
//		tmp.setFloorarea(item.getFloorarea());
//		tmp.setRentprice(item.getRentprice());
//		tmp.setBrokeragefee(item.getBrokeragefee());
//		tmp.setServicefee(item.getServicefee());
		return tmp;
	}
}
