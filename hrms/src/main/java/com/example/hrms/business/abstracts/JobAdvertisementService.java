package com.example.hrms.business.abstracts;

import com.example.hrms.core.utilities.DataResult;

import com.example.hrms.core.utilities.Result;
import com.example.hrms.entities.concretes.JobAdvertisement;
import com.example.hrms.entities.dtos.JobAdvertisementDto;

import java.util.List;

public interface JobAdvertisementService {

    List<JobAdvertisement> findAllJobAdvertisement();
    List<JobAdvertisement> getAllSortPosting();
    List<JobAdvertisement> findAllByEmployer_EmployerId(Integer employerId);

    Result updateJobAdvertisementStatus(Integer id, boolean isActive);
    Result add(JobAdvertisement jobAdvertisement);
}
