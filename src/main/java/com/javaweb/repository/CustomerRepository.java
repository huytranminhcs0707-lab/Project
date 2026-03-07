package com.javaweb.repository;


import com.javaweb.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    List<CustomerEntity> findDistinctByFullnameContainingAndPhoneContainingAndEmailContainingAndUserId(
            String fullname,
            String phone,
            String email,
            Long userId
    );
    List<CustomerEntity> findDistinctByFullnameContainingAndPhoneContainingAndEmailContaining(
            String fullname,
            String phone,
            String email
    );
    List<CustomerEntity> findByIdIn(List<Long> ids);
}
