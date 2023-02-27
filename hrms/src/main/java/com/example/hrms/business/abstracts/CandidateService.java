package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.DataResult;
import com.example.hrms.core.utilities.Result;
import com.example.hrms.core.utilities.SuccessDataResult;
import com.example.hrms.entities.concretes.Candidate;
import com.example.hrms.entities.concretes.VerificationCode;

public interface CandidateService {
	Result add(Candidate candidate);
	//List<Candidate> getAll();
	SuccessDataResult<List<Candidate>> getAll();
	Result verifyCandidate(String email,String code);
	}

