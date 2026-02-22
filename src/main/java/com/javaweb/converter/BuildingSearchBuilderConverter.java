package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.Utils.MapUtil;
import com.javaweb.builder.BuildingSearchBuilder;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map <String, Object> params, List<String> typecode) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
																				.setName(MapUtil.getObject(params, "name", String.class))
																				.setDistrictid(MapUtil.getObject(params, "districtid", Integer.class))
																				.setFloorarea(MapUtil.getObject(params, "floorarea", Integer.class))
																				.setWard(MapUtil.getObject(params, "ward", String.class))
																				.setStreet(MapUtil.getObject(params, "street", String.class))
																				.setNumberofbasement(MapUtil.getObject(params, "numberofbasement", Integer.class))
																				.setLevel(MapUtil.getObject(params, "level", Integer.class))
																				.setTypecode(typecode)
																				.setAreafrom(MapUtil.getObject(params, "areafrom", Integer.class))
																				.setAreato(MapUtil.getObject(params, "areato", Integer.class))
																				.setRentpricefrom(MapUtil.getObject(params, "rentpricefrom", Integer.class))
																				.setRentpriceto(MapUtil.getObject(params, "rentpriceto", Integer.class))
																				.setManagername(MapUtil.getObject(params, "managername", String.class))
																				.setManagerphonenumber(MapUtil.getObject(params, "managerphonenumber", String.class))
																				.setDirection(MapUtil.getObject(params, "direction", String.class))
																				.setStaffid(MapUtil.getObject(params, "staffid", Integer.class))
																				.build();
		return buildingSearchBuilder;
	}
}
