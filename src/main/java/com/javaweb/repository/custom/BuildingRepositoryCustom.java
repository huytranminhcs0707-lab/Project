package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> find(BuildingSearchBuilder buildingSearchBuilder);
    Page<BuildingEntity> find(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable);
    void deleteBuildingorListBuilding(List<Long> ids);
}
