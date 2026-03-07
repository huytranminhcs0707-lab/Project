    package com.javaweb.controller.admin;

    import com.javaweb.enums.TransactionType;
    import com.javaweb.enums.buildingType;
    import com.javaweb.enums.districtCode;
    import com.javaweb.model.dto.BuildingDTO;
    import com.javaweb.model.dto.CustomerDTO;
    import com.javaweb.model.dto.TransactionDTO;
    import com.javaweb.model.request.BuildingSearchRequest;
    import com.javaweb.model.response.BuildingSearchResponse;
    import com.javaweb.security.utils.SecurityUtils;
    import com.javaweb.service.CustomerService;
    import com.javaweb.service.impl.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;

    import javax.servlet.http.HttpServletRequest;
    import java.util.ArrayList;
    import java.util.List;

    @Controller
    public class CustomerController {
        @Autowired
        private UserService userService;
        @Autowired
        private CustomerService customerService;
        @GetMapping(value = "/admin/customer-list")
        public ModelAndView customerList(@ModelAttribute CustomerDTO customerDTO){
            ModelAndView mav = new ModelAndView("admin/customer/list");
            if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
                Long staffId = SecurityUtils.getPrincipal().getId();
                customerDTO.setStaffId(staffId);
            }
            List<CustomerDTO> customerDTOList = customerService.find(customerDTO);
            mav.addObject("modelSearch", customerDTO);
            mav.addObject("customerList", customerDTOList);
            mav.addObject("listStaffs",userService.getStaffs());
            return mav;
        }
        @GetMapping(value = "/admin/customer-edit")
        public ModelAndView customerEdit(@ModelAttribute CustomerDTO customerDTO, HttpServletRequest request){
            ModelAndView mav = new ModelAndView("admin/customer/edit");
            mav.addObject("customerEdit",customerDTO);
            return mav;
        }
        @GetMapping(value = "/admin/customer-edit-{id}")
        public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request){
            ModelAndView mav = new ModelAndView("admin/customer/edit");
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO = customerService.showCustomerDetail(id);
            System.out.println("oke");
            List<TransactionDTO> list1 = customerService.findByCodeAndCustomerId("CSKH",id);
            List<TransactionDTO> list2 = customerService.findByCodeAndCustomerId("DDX",id);
            mav.addObject("list1",list1);
            mav.addObject("list2",list2);
            mav.addObject("customerEdit",customerDTO);
            mav.addObject("transactionType", TransactionType.transactionType());
            return mav;
        }
    }
