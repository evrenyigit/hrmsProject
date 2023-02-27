package com.example.hrms.api.controllers;

import java.util.List;
import java.util.Map;

import com.example.hrms.core.utilities.DataResult;
import com.example.hrms.core.utilities.Result;
import com.example.hrms.entities.concretes.EmployeeConfirm;
import com.example.hrms.entities.concretes.Employer;
import com.example.hrms.entities.concretes.User;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hrms.business.abstracts.EmployeeService;
import com.example.hrms.entities.concretes.Employee;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeesController(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Employee>> getAll() {

		return employeeService.getAll();
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Employee employee) {
		Result result = employeeService.add(employee);

		if(result.isSuccess()) {
			return ResponseEntity.ok().body(result);
		}else{
			return ResponseEntity.badRequest().body(result);
		}

	}

	@PostMapping("/confirm")
	public Result confirm(@RequestBody Map<String,String> request){
		Integer confirmId = Integer.valueOf(request.get("confirmId")); // employeeConfirmId
		Integer employeeId = Integer.valueOf(request.get("employeeId")); // employee id
		Boolean confirmed = Boolean.valueOf(request.get("confirmed")); // true or false
		return employeeService.confirmEmployer(confirmId,employeeId,confirmed);
	}

}