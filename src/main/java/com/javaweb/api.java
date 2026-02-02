package com.javaweb;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;
@RestController
public class api {
	@Autowired
	BuildingService buildingService;
	@RequestMapping(value = "/api/building/", method = RequestMethod.GET)
	public List<BuildingDTO> getBuilding(@RequestParam(required = false) Map <String, Object> params, @RequestParam(name ="typecode",required = false) List<String> typecode) {
		List<BuildingDTO> result = buildingService.find(params, typecode);
		return result;
	}
	
	
//	public void validate(BuildingDTO buildingDTO){
//		List<String> error = new ArrayList<String>();
//		if (buildingDTO.getName() == null || buildingDTO.getName().equals("")) {
//			error.add("name is null!");
//		}
//		if (buildingDTO.getNumberofbase() < 0) {
//			error.add("Number of basement cannot smaller than 0");
//		}
//		if (buildingDTO.getStreet() == null) {
//			error.add("street is null!");
//		}
//		if (!error.isEmpty()) {
//			throw new FieldRequiredException(error);
//		}
//	}
//	@RequestMapping(value = "/api/building/", method = RequestMethod.POST)
//	
//	public BuildingDTO getBuilding2(@RequestBody BuildingDTO buildingDTO) {
//		 return buildingDTO;
//	}
	@DeleteMapping(value = "/api/building/{id}/{name}/")
	public void deleteBuilding(@PathVariable Integer id, @PathVariable String name, @RequestParam(value = "ward") String ward) {
		System.out.print(id + name + ward);
	}
}
