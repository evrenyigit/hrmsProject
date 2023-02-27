package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.DataResult;
import com.example.hrms.core.utilities.Result;
import com.example.hrms.entities.concretes.Employer;
import com.example.hrms.entities.concretes.VerificationCode;

public interface EmployerService {

	Result add(Employer employer); 
	DataResult<List<Employer>> getAll();
	Result verifyEmployer(String email, String code);
}
