package com.example.hrms.core.verifications.abstracts;

import com.example.hrms.core.utilities.Result;

public interface VerificationCodeService {
	Result sendCode(String email,String code);
}
