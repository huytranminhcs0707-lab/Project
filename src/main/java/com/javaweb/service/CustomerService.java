package com.javaweb.service;

import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> find(CustomerDTO customerDTO);
    void addOrUpdateCustomer(CustomerDTO customerDTO);
    CustomerDTO showCustomerDetail(Long id);
    void deleteCustomer(List<Long> ids);
    ResponseDTO loadStaffs(Long id);
    void assignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO);
    void addOrUpdateTransaction(TransactionDTO transactionDTO);
    List<TransactionDTO> findByCodeAndCustomerId(String code, Long customerId);
}
