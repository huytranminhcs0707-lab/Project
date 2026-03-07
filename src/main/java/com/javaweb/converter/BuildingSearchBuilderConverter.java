package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import com.javaweb.model.request.BuildingSearchRequest;
import org.springframework.stereotype.Component;

import com.javaweb.utils.MapUtils;
import com.javaweb.builder.BuildingSearchBuilder;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObject(buildingSearchRequest.getName(),String.class))
                .setDistrict(MapUtils.getObject(buildingSearchRequest.getDistrict(),String.class))
                .setFloorArea(MapUtils.getObject(buildingSearchRequest.getFloorArea(),Long.class))
                .setWard(MapUtils.getObject(buildingSearchRequest.getWard(),String.class))
                .setStreet(MapUtils.getObject(buildingSearchRequest.getStreet(),String.class))
                .setNumberOfBasement(MapUtils.getObject(buildingSearchRequest.getNumberOfBasement(),Long.class))
                .setLevel(MapUtils.getObject(buildingSearchRequest.getLevel(),Long.class))
                .setTypeCode(buildingSearchRequest.getTypeCode())
                .setAreaFrom(MapUtils.getObject(buildingSearchRequest.getAreaFrom(),Long.class))
                .setAreaTo(MapUtils.getObject(buildingSearchRequest.getAreaTo(),Long.class))
                .setRentPriceFrom(MapUtils.getObject(buildingSearchRequest.getRentPriceFrom(),Long.class))
                .setRentPriceTo(MapUtils.getObject(buildingSearchRequest.getRentPriceTo(),Long.class))
                .setManagerName(MapUtils.getObject(buildingSearchRequest.getManagerName(),String.class))
                .setManagerPhoneNumber(MapUtils.getObject(buildingSearchRequest.getManagerPhoneNumber(),String.class))
                .setDirection(MapUtils.getObject(buildingSearchRequest.getDirection(),String.class))
                .setStaffId(MapUtils.getObject(buildingSearchRequest.getStaffId(),Long.class))
                .build();
        return buildingSearchBuilder;
    }
}
