package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchResponseConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Autowired
    private BuildingSearchResponseConverter buildingSearchResponseConverter;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseDTO loadStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignment = building.getUsers();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity item1 : staffs){
            boolean checked = false;
            for (UserEntity item2 : staffAssignment){
                if (item1.getId() == item2.getId()){
                    checked = true;
                }
            }
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(item1.getId());
            staffResponseDTO.setFullName(item1.getFullName());
            if (checked){
                staffResponseDTO.setChecked("checked");
            }
            else{
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> find(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> building = buildingRepository.find(buildingSearchBuilder);
        List<BuildingSearchResponse> result = new ArrayList<>();
        for (BuildingEntity item : building) {
            result.add(buildingSearchResponseConverter
                    .toBuildingSearchResponse(item));
        }
        return result;
    }
    @Override
    public Page<BuildingSearchResponse> find(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);

        Page<BuildingEntity> buildingPage = buildingRepository.find(buildingSearchBuilder, pageable);

        List<BuildingSearchResponse> content = buildingPage.getContent()
                .stream()
                .map(item -> buildingSearchResponseConverter.toBuildingSearchResponse(item))
                .collect(Collectors.toList());

        return new PageImpl<>(content, pageable, buildingPage.getTotalElements());
    }

    @Override
    public BuildingDTO showBuilding(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = new BuildingDTO();
        modelMapper.map(buildingEntity, buildingDTO);
        //xu ly typecode
        String type = buildingEntity.getType();

        if (type != null && !type.trim().isEmpty()) {
            List<String> typeCode = Arrays.asList(type.split(","));
            buildingDTO.setTypeCode(typeCode);
        }
        //xu ly rentarea
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentareas();
        String rentAreaResult = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        buildingDTO.setRentArea(rentAreaResult);
        return buildingDTO;
    }

    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity;
        if (buildingDTO.getId() != null) {
            buildingEntity = buildingRepository.findById(buildingDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Building not found"));
        } else {
            buildingEntity = new BuildingEntity();
        }

        modelMapper.map(buildingDTO, buildingEntity);
        // xử lý type
        if (buildingDTO.getTypeCode() != null) {
            buildingEntity.setType(String.join(",", buildingDTO.getTypeCode()));
        }

        if (buildingDTO.getRentArea() != null && !buildingDTO.getRentArea().isEmpty()) {
            buildingEntity.getRentareas().clear();
                String[] areas = buildingDTO.getRentArea().split(",");
                for (String item : areas) {
                    RentAreaEntity rentArea = new RentAreaEntity();
                    rentArea.setValue(item);
                    rentArea.setBuilding(buildingEntity);
                   buildingEntity.getRentareas().add(rentArea);
                }


                }

        System.out.println("oke");
        buildingRepository.save(buildingEntity);
    }

    @Override
    public void deleteBuildingorListBuilding(List<Long> ids) {
        buildingRepository.deleteBuildingorListBuilding(ids);
    }

    @Override
    public void addAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
           BuildingEntity buildingEntity= buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
           List<UserEntity> userEntityList = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
           buildingEntity.setUsers(userEntityList);
           buildingRepository.save(buildingEntity);
    }
}
