package com.example.hrms.api.controllers;

import com.example.hrms.core.utilities.DataResult;

import com.example.hrms.entities.concretes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.CandidateService;
import com.example.hrms.core.utilities.Result;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {


	private CandidateService candidateService;
	
	@Autowired
	public CandidatesController(CandidateService candidateService) {

		this.candidateService = candidateService;
	
	}
	
	@GetMapping("/getall")
	public DataResult<List<Candidate>> getAll(){

		return candidateService.getAll();

		/* try{
			return ResponseEntity.ok().body(candidateDao.findAll());

		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}*/
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Candidate candidate) {
		Result result = candidateService.add(candidate);
		if(result.isSuccess()) 
			return ResponseEntity.ok().body(result);
		else
			return ResponseEntity.badRequest().body(result);
		}

	@PostMapping("/verify")
	public Result verify(@RequestBody Map<String,String> request){
		String email = request.get("email");
		String code = request.get("code");
		return candidateService.verifyCandidate(email, code);
	}

}