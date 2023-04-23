package com.example.hrms.business.abstracts;

import com.example.hrms.core.utilities.DataResult;
import com.example.hrms.entities.concretes.JobAdvertisement;
import com.example.hrms.entities.dtos.JobAdvertisementDto;

import java.util.List;

public interface JobAdvertisementDtoService {

    DataResult<List<JobAdvertisementDto>> getAllJobAdvertisementsAsDto(List<JobAdvertisement> jobAdvertisementList);
}
