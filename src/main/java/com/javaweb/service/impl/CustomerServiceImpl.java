package com.javaweb.service.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public List<CustomerDTO> find(CustomerDTO customerDTO) {
        List<CustomerEntity> customerEntityList = new ArrayList<>();
        String name = customerDTO.getName() != null ? customerDTO.getName(): "";
        String phone = customerDTO.getCustomerPhone() != null ? customerDTO.getCustomerPhone(): "";
        String email = customerDTO.getEmail() != null ? customerDTO.getEmail(): "";
        if (customerDTO.getStaffId()!= null){
             customerEntityList = customerRepository.findDistinctByFullnameContainingAndPhoneContainingAndEmailContainingAndUserId(name,phone,email,customerDTO.getStaffId());
        }
        else{
            customerEntityList = customerRepository.findDistinctByFullnameContainingAndPhoneContainingAndEmailContaining(name, phone, email);
        }
        List<CustomerDTO> result = new ArrayList<>();
        for (CustomerEntity item : customerEntityList){
            CustomerDTO tmp = new CustomerDTO();
            tmp.setId(item.getId());
            tmp.setName(item.getFullname());
            tmp.setCustomerPhone(item.getPhone());
            tmp.setDemand(item.getDemand());
            tmp.setEmail(item.getEmail());
            tmp.setCreatedBy(item.getCreatedBy());
            tmp.setCreatedDate(item.getCreatedDate());
            tmp.setStatus(item.getStatus());
            result.add(tmp);
        }
        return result;
    }

    @Override
    public void addOrUpdateCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity;
        if (customerDTO.getId()!= null){
            customerEntity = customerRepository.findById(customerDTO.getId()).get();
        }
        else{
            customerEntity = new CustomerEntity();
        }
        customerEntity.setFullname(customerDTO.getName());
        customerEntity.setPhone(customerDTO.getCustomerPhone());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setCompanyname(customerDTO.getCompanyName());
        customerEntity.setDemand(customerDTO.getDemand());
        customerEntity.setStatus(customerDTO.getStatus());
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerDTO showCustomerDetail(Long id) {
       CustomerDTO result = new CustomerDTO();
       CustomerEntity customerEntity = customerRepository.findById(id).get();
       result.setId(id);
       result.setName(customerEntity.getFullname());
       result.setCustomerPhone(customerEntity.getPhone());
       result.setEmail(customerEntity.getEmail());
       result.setCompanyName(customerEntity.getCompanyname());
       result.setStatus(customerEntity.getStatus());
       result.setDemand(customerEntity.getDemand());
       return result;
    }

    @Override
    public void deleteCustomer(List<Long> ids) {
        List<CustomerEntity> customerEntityList = customerRepository.findByIdIn(ids);
        for (CustomerEntity item : customerEntityList){
            customerRepository.delete(item);
        }
        System.out.println("oke");
    }

    @Override
    public ResponseDTO loadStaffs(Long id) {
        ResponseDTO result = new ResponseDTO();
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        List<UserEntity> staffAssignment = customerEntity.getUser();
        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<StaffResponseDTO> staffResponseDTOList = new ArrayList<>();
        for (UserEntity item : allStaffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(item.getId());
            staffResponseDTO.setFullName(item.getFullName());
            if (staffAssignment.contains(item)){
                staffResponseDTO.setChecked("checked");
            }
            else{
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOList.add(staffResponseDTO);
        }
        result.setData(staffResponseDTOList);
        result.setMessage("success");
        return result;
    }

    @Override
    public void assignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        List<UserEntity> staffs = userRepository.findByIdIn(assignmentCustomerDTO.getStaffs());
        customerEntity.setUser(staffs);
        customerRepository.save(customerEntity);
    }

    @Override
    public void addOrUpdateTransaction(TransactionDTO transactionDTO) {
        CustomerEntity customerEntity = customerRepository.findById(transactionDTO.getCustomerId()).get();
        TransactionEntity transactionEntity;
        if (transactionDTO.getId()!= null){
            transactionEntity = transactionRepository.findById(transactionDTO.getId()).get();
        }
        else{
                transactionEntity = new TransactionEntity();
                transactionEntity.setCreatedDate(transactionDTO.getCreatedDate());
                transactionEntity.setCreatedBy(transactionDTO.getCreatedBy());
                transactionEntity.setCode(transactionDTO.getCode());
        }
        transactionEntity.setNote(transactionDTO.getTransactionDetail());
        transactionEntity.setCustomer(customerEntity);
        transactionRepository.save(transactionEntity);

    }

    @Override
    public List<TransactionDTO> findByCodeAndCustomerId(String code, Long customerId) {
       List<TransactionDTO> result = new ArrayList<>();
       CustomerEntity customerEntity = customerRepository.findById(customerId).get();
       List<TransactionEntity> transactionEntities = customerEntity.getTransactions();
       for (TransactionEntity item : transactionEntities){
           TransactionDTO tmp = new TransactionDTO();
           if (item.getCode().equals(code)){
               tmp.setId(item.getId());
               tmp.setCode(item.getCode());
               tmp.setCustomerId(customerId);
               tmp.setTransactionDetail(item.getNote());
               tmp.setCreatedBy(item.getCreatedBy());
               tmp.setCreatedDate(item.getCreatedDate());
               result.add(tmp);
           }

       }
       return  result;
    }
}
