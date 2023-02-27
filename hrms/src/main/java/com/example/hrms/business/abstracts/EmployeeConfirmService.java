package com.example.hrms.business.abstracts;

import com.example.hrms.core.utilities.SuccessDataResult;
import com.example.hrms.entities.concretes.EmployeeConfirm;

import java.util.List;

public interface EmployeeConfirmService {

    SuccessDataResult<List<EmployeeConfirm>> getEmployer();

}
