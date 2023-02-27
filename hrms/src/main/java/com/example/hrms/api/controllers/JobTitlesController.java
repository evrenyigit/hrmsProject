package com.example.hrms.api.controllers;

import java.util.List;

import com.example.hrms.core.utilities.Result;
import com.example.hrms.entities.concretes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.JobTitleService;
import com.example.hrms.core.utilities.DataResult;
import com.example.hrms.entities.concretes.JobTitle;

@RestController
@RequestMapping("/api/jobTitles")
public class JobTitlesController {

	@Autowired
	private JobTitleService jobTitleService;

	public JobTitlesController(JobTitleService jobTitleService) {
		this.jobTitleService = jobTitleService;
	}

	@GetMapping("/getall")
	public DataResult<List<JobTitle>> getAll(){
		return jobTitleService.getAll();
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody JobTitle jobTitle){
		Result result = jobTitleService.add(jobTitle);
		if(result.isSuccess())
			return ResponseEntity.ok().body(result);
		else
			return ResponseEntity.badRequest().body(result);
	}



}