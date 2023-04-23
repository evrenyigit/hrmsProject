package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.JobAdvertisementDtoService;
import com.example.hrms.business.abstracts.JobAdvertisementService;
import com.example.hrms.core.utilities.DataResult;
import com.example.hrms.core.utilities.Result;
import com.example.hrms.entities.concretes.Employer;
import com.example.hrms.entities.concretes.JobAdvertisement;
import com.example.hrms.entities.dtos.JobAdvertisementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobAdvertisement")
public class JobAdvertisementController {
    private JobAdvertisementService jobAdvertisementService;
    private JobAdvertisementDtoService jobAdvertisementDtoService;

    @Autowired
    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService,JobAdvertisementDtoService jobAdvertisementDtoService){
        this.jobAdvertisementService = jobAdvertisementService;
        this.jobAdvertisementDtoService = jobAdvertisementDtoService;
    }

    @GetMapping("/getAll")
    public DataResult<List<JobAdvertisementDto>> getAllJobAdvertisementDetails(){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementService.findAllJobAdvertisement();
        return this.jobAdvertisementDtoService.getAllJobAdvertisementsAsDto(jobAdvertisementList);
    }
    @GetMapping("/getAllSortPosting")
    public DataResult<List<JobAdvertisementDto>> getAllSortPosting(){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementService.getAllSortPosting();
        return this.jobAdvertisementDtoService.getAllJobAdvertisementsAsDto(jobAdvertisementList);
    }

    @GetMapping("/getAllByEmployer/{employerId}")
    public DataResult<List<JobAdvertisementDto>> getAllByEmployer(@PathVariable Integer employerId){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementService.findAllByEmployer_EmployerId(employerId);
        return this.jobAdvertisementDtoService.getAllJobAdvertisementsAsDto(jobAdvertisementList);
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobAdvertisement jobAdvertisement){
        return this.jobAdvertisementService.add(jobAdvertisement);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateJobAdvertisementStatus(@PathVariable("id") Integer id, @RequestBody Boolean isActive) {
        Result result = jobAdvertisementService.updateJobAdvertisementStatus(id, isActive);
        return ResponseEntity.ok(result);
    }
}