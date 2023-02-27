package com.example.hrms.business.concretes;

import java.util.List;

import com.example.hrms.core.utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.JobTitleService;
import com.example.hrms.dataAccess.abstracts.JobTitleDao;
import com.example.hrms.entities.concretes.JobTitle;

@Service
public class JobTitleManager implements JobTitleService{
	
	@Autowired
	private JobTitleDao jobTitleDao;

	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public DataResult<List<JobTitle>> getAll() {
		
		return new SuccessDataResult<List<JobTitle>>("Job titles are listed." , jobTitleDao.findAll());
	}

	@Override
	public Result add(JobTitle jobTitle) {
		if(jobTitleDao.existsByJobName(jobTitle.getJobName()))
			return new ErrorResult("Aynı isimle bir iş pozisyonu mevcut.");
		jobTitleDao.save(jobTitle);
		return new SuccessResult("İş pozisyonu kaydedildi.");
	}

}
