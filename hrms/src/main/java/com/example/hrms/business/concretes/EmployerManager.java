package com.example.hrms.business.concretes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.hrms.entities.concretes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.EmployerService;
import com.example.hrms.core.utilities.DataResult;
import com.example.hrms.core.utilities.ErrorResult;
import com.example.hrms.core.utilities.Result;
import com.example.hrms.core.utilities.SuccessDataResult;
import com.example.hrms.core.utilities.SuccessResult;
import com.example.hrms.core.validations.abstracts.ValidationService;
import com.example.hrms.core.verifications.abstracts.VerificationCodeService;
import com.example.hrms.dataAccess.abstracts.EmployeeConfirmDao;
import com.example.hrms.dataAccess.abstracts.EmployerDao;
import com.example.hrms.dataAccess.abstracts.UserDao;
import com.example.hrms.dataAccess.abstracts.VerificationCodeDao;


@Service
public class EmployerManager implements EmployerService{
	
	private ValidationService validationService;
	private EmployeeConfirmDao employeeConfirmDao;
	private EmployerDao employerDao;
	private VerificationCodeService verificationCodeService;
	private UserDao userDao;
	private VerificationCodeDao verificationCodeDao;
	
	@Autowired
	EmployerManager(ValidationService validationService , EmployeeConfirmDao employeeConfirmDao ,
					EmployerDao employerDao, VerificationCodeService verificationCodeService,
			UserDao userDao,VerificationCodeDao verificationCodeDao) {
		this.validationService = validationService;
		this.employeeConfirmDao = employeeConfirmDao;
		this.employerDao = employerDao;
		this.verificationCodeService = verificationCodeService;
		this.userDao = userDao;
		this.verificationCodeDao = verificationCodeDao;
	}
	

	@Override
	public Result add(Employer employer) {
		
		Result result = validationService.isAllSpaceFullEmployer(employer);
		// domain DONE , mail doğrulaması DONE , employee confirm employer eksik
		if(result.isSuccess()) {
			User user = employer.getUser();
			userDao.save(user);
			List<VerificationCode> verificationCodeList = new ArrayList<>();
			VerificationCode verificationCode = new VerificationCode(UUID.randomUUID().toString(),false,new Date());
			verificationCodeService.sendCode(employer.getUser().getEmail(), verificationCode.getCode());
			verificationCode = verificationCodeDao.save(verificationCode);
			verificationCodeList.add(verificationCode);
			employer.setVerificationCodeList(verificationCodeList);
			employer = employerDao.save(employer);
			result = new SuccessResult("Employer kaydı oluşturuldu lütfen email doğrulaması yapınız.");
		} 
		return result;	
	}
	
	@Override
	public Result verifyEmployer(String email, String code) {
		Integer verify = employerDao.dogrulamaEmployerMail(email,code);
		if(verify==null)
			return new ErrorResult("Doğrulama başarısız.");
		VerificationCode verificationCode = verificationCodeDao.findById(verify).get();
		if(verificationCode.isVerified()){
			return new ErrorResult("Daha önce doğrulandı");
		}else{
			verificationCode.setVerified(true);
			verificationCodeDao.save(verificationCode);
			List<EmployeeConfirm> employeeConfirmList = new ArrayList<>();
			EmployeeConfirm employeeConfirm = new EmployeeConfirm(null,false,new Date());
			Employer employer = employerDao.findByUser_Email(email);
			employeeConfirmDao.save(employeeConfirm);
			employeeConfirmList.add(employeeConfirm);
			employer.setEmployeeConfirmList(employeeConfirmList);
			employerDao.save(employer);
		}

		return new SuccessResult("emailiniz onaylandı çalışanlarımızın şirketinizi onaylamasını bekleyin.");
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>("Employers listed.",employerDao.findAll());
	}
	
}