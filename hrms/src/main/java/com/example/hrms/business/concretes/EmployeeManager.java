package com.example.hrms.business.concretes;

import java.util.List;

import com.example.hrms.core.utilities.ErrorResult;
import com.example.hrms.core.utilities.Result;
import com.example.hrms.core.utilities.SuccessDataResult;
import com.example.hrms.core.utilities.SuccessResult;
import com.example.hrms.core.validations.abstracts.ValidationService;
import com.example.hrms.dataAccess.abstracts.EmployeeConfirmDao;
import com.example.hrms.dataAccess.abstracts.PeopleDetailDao;
import com.example.hrms.dataAccess.abstracts.UserDao;
import com.example.hrms.entities.concretes.EmployeeConfirm;
import com.example.hrms.entities.concretes.PeopleDetail;
import com.example.hrms.entities.concretes.User;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.EmployeeService;
import com.example.hrms.dataAccess.abstracts.EmployeeDao;
import com.example.hrms.entities.concretes.Employee;

@Service
public class EmployeeManager implements EmployeeService {

    private EmployeeDao employeeDao;
    private ValidationService validationService;
    private UserDao userDao;
    private PeopleDetailDao peopleDetailDao;
    private EmployeeConfirmDao employeeConfirmDao;

    public EmployeeManager(EmployeeDao employeeDao, ValidationService validationService, UserDao userDao, PeopleDetailDao peopleDetailDao,
    EmployeeConfirmDao employeeConfirmDao) {

        this.employeeDao = employeeDao;
        this.validationService = validationService;
        this.userDao = userDao;
        this.peopleDetailDao = peopleDetailDao;
        this.employeeConfirmDao = employeeConfirmDao;
    }

    @Override
    public Result add(Employee employee) {
        Result result = validationService.isAllSpaceFullEmployee(employee);

        if (result.isSuccess()) {
            User user = employee.getPeopleDetail().getUser();
            user = userDao.save(user);
            PeopleDetail peopleDetail = employee.getPeopleDetail();
            peopleDetail = peopleDetailDao.save(peopleDetail);
            employee = employeeDao.save(employee);
            return new SuccessResult("Employee added successfully.");

        } else {

            return result;
        }

    }
    @Override
    public Result confirmEmployer(Integer confirmId,Integer employeeId,Boolean confirmed){
        EmployeeConfirm employeeConfirm = employeeConfirmDao.findById(confirmId).get();
        employeeConfirm.setConfirmed(confirmed);
        employeeConfirm.setEmployeeId(employeeId);
        employeeConfirmDao.save(employeeConfirm);
        if(confirmed == false)
            return new ErrorResult("Çalışanlar kaydınızı onaylamadı.");
        return new SuccessResult("Sistem çalışanları kaydınızı onayladı giriş yapabilirsiniz.");
    }

    @Override
    public SuccessDataResult<List<Employee>> getAll() {
        return new SuccessDataResult<List<Employee>>("employees listed", employeeDao.findAll());
    }

}