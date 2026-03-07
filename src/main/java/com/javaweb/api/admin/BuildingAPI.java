package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    @PostMapping(value = "/api/building")
    public void addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        buildingService.addOrUpdateBuilding(buildingDTO);
        System.out.println("oke");
    }
    @DeleteMapping(value = "/api/building/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids){
        buildingService.deleteBuildingorListBuilding(ids);

    }
    @GetMapping(value = "/api/building/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO result = buildingService.loadStaffs(id);
        return result;
    }
    @PostMapping(value = "/api/building/assignment")
    public void assignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        System.out.println("oke");
        buildingService.addAssignmentBuilding(assignmentBuildingDTO);

    }
}
