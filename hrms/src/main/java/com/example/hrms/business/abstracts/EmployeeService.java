package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.Result;
import com.example.hrms.core.utilities.SuccessDataResult;
import com.example.hrms.entities.concretes.Employee;
import com.example.hrms.entities.concretes.EmployeeConfirm;

public interface EmployeeService {
	Result add(Employee employee);

	Result confirmEmployer(Integer confirmId,Integer employeeId,Boolean confirmed);
	SuccessDataResult<List<Employee>> getAll();

}
