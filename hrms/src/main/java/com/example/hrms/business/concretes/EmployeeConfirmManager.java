package com.example.hrms.business.concretes;

import com.example.hrms.business.abstracts.EmployeeConfirmService;
import com.example.hrms.core.utilities.SuccessDataResult;
import com.example.hrms.dataAccess.abstracts.EmployeeConfirmDao;
import com.example.hrms.entities.concretes.EmployeeConfirm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeConfirmManager implements EmployeeConfirmService {

    private EmployeeConfirmDao employeeConfirmDao;

    public EmployeeConfirmManager(EmployeeConfirmDao employeeConfirmDao){
        this.employeeConfirmDao = employeeConfirmDao;
    }
    @Override
    public SuccessDataResult<List<EmployeeConfirm>> getEmployer() {
        return new SuccessDataResult<List<EmployeeConfirm>>("employers to wait confirm",
                employeeConfirmDao.findAllByConfirmedIsFalseAndEmployeeIdIsNull());
    }
}
