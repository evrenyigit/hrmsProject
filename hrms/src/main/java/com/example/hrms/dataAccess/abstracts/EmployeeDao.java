package com.example.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hrms.entities.concretes.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Integer>{

}
