package com.example.hrms.core.verifications.concretes;

import com.example.hrms.dataAccess.abstracts.VerificationCodeDao;
import com.example.hrms.entities.concretes.VerificationCode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.hrms.core.utilities.Result;
import com.example.hrms.core.utilities.SuccessResult;
import com.example.hrms.core.verifications.abstracts.VerificationCodeService;

import java.util.List;

@Service
public class VerificationCodeManager implements VerificationCodeService {
	private VerificationCodeDao verificationCodeDao;

	public VerificationCodeManager(VerificationCodeDao verificationCodeDao) {
		this.verificationCodeDao = verificationCodeDao;
	}

	@Override
	public Result sendCode(String email,String code) {
		return new SuccessResult("Doğrulama maili" + email + "adresine gönderildi." + code);
	}

}