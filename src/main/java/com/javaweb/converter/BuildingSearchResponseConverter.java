package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingSearchResponseConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity item){
        BuildingSearchResponse tmp = modelMapper.map(item, BuildingSearchResponse.class);
        List<RentAreaEntity> rentAreaEntities = item.getRentareas();
        String rentAreaResult = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        tmp.setRentArea(rentAreaResult);
        String districtname = districtCode.getNameByDistrictCode(item.getDistrict());
        tmp.setAddress("Đường " + item.getStreet() + ", " +item.getWard() + ", " + districtname );
        return tmp;
    }
}
