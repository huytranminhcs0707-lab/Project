package com.javaweb.controller.admin;



import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;
    @Autowired
    private BuildingService buildingService;
    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "3") int size, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch", buildingSearchRequest);
        //xu ly db
        if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(staffId);
        }
        // Tạo Pageable từ page và size
        Pageable pageable = PageRequest.of(page, size);
        Page<BuildingSearchResponse> pageResult = buildingService.find(buildingSearchRequest, pageable);

        mav.addObject("buildingList", pageResult.getContent());
        mav.addObject("currentPage", page);
        mav.addObject("totalPages", pageResult.getTotalPages());
        mav.addObject("totalElements", pageResult.getTotalElements());
        mav.addObject("size", size);
        mav.addObject("listStaffs",userService.getStaffs());
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }
    @GetMapping(value = "/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute BuildingDTO buildingDTO, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("buildingEdit",buildingDTO);
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }
    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = buildingService.showBuilding(id);
        mav.addObject("buildingEdit",buildingDTO);
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }


}
