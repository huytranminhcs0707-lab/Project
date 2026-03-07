package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingService {
    ResponseDTO loadStaffs(Long buildingId);
    List<BuildingSearchResponse> find(BuildingSearchRequest buildingSearchRequest);
    Page<BuildingSearchResponse> find(BuildingSearchRequest buildingSearchRequest, Pageable pageable);
    BuildingDTO showBuilding(Long id);
    void addOrUpdateBuilding(BuildingDTO buildingDTO);
    void deleteBuildingorListBuilding(List<Long> ids);
    void addAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
}
