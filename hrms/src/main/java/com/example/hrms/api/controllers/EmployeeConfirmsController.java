package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.EmployeeConfirmService;
import com.example.hrms.core.utilities.DataResult;
import com.example.hrms.entities.concretes.EmployeeConfirm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/employeeConfirms")
public class EmployeeConfirmsController {

    private EmployeeConfirmService employeeConfirmService;

    public EmployeeConfirmsController(EmployeeConfirmService employeeConfirmService){
        this.employeeConfirmService = employeeConfirmService;
    }
    @GetMapping("/waitingRequests")
    public DataResult<List<EmployeeConfirm>> getEmployers() {
        return employeeConfirmService.getEmployer();
    }
}
