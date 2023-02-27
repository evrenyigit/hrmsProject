package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.concretes.EmployeeConfirm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeConfirmDao extends JpaRepository<EmployeeConfirm,Integer>{
    List<EmployeeConfirm> findAllByConfirmedIsFalseAndEmployeeIdIsNull();
}
