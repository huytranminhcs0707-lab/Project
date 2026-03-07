package com.javaweb.api.admin;

import com.javaweb.model.dto.*;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerAPI {
    @Autowired
    CustomerService customerService;
    @PostMapping(value = "/api/customer")
    public void addOrUpdateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.addOrUpdateCustomer(customerDTO);
        System.out.println("oke");
    }
    @DeleteMapping(value = "/api/customer/{ids}")
    public void deleteCustomer(@PathVariable List<Long> ids){
        customerService.deleteCustomer(ids);

    }
    @GetMapping(value = "/api/customer/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO result = customerService.loadStaffs(id);
        return result;
    }
    @PostMapping(value = "/api/customer/assignment")
    public void assignmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        System.out.println("oke");
        customerService.assignmentCustomer(assignmentCustomerDTO);

    }
    @PostMapping(value = "/api/customer/transaction")
    public void addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO){
        System.out.println("oke");
        customerService.addOrUpdateTransaction(transactionDTO);

    }
}
