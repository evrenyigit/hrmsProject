package com.example.hrms.business.concretes;

import com.example.hrms.business.abstracts.JobAdvertisementDtoService;
import com.example.hrms.core.utilities.DataResult;
import com.example.hrms.core.utilities.SuccessDataResult;
import com.example.hrms.entities.concretes.JobAdvertisement;
import com.example.hrms.entities.dtos.JobAdvertisementDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobAdvertisementDtoManager implements JobAdvertisementDtoService {


    @Override
    public DataResult<List<JobAdvertisementDto>> getAllJobAdvertisementsAsDto(List<JobAdvertisement> jobAdvertisementList) {
        List<JobAdvertisementDto> jobAdvertisementDtoList = new ArrayList<>();

        for(JobAdvertisement jobAdvertisement : jobAdvertisementList){
            JobAdvertisementDto jobAdvertisementDto = new JobAdvertisementDto();
            jobAdvertisementDto.setJobDescription(jobAdvertisement.getJobDescription());
            jobAdvertisementDto.setJobTitleName(jobAdvertisement.getJobTitle().getJobName());
            jobAdvertisementDto.setEmployerName(jobAdvertisement.getEmployer().getCompanyName());
            jobAdvertisementDto.setPostingDate(jobAdvertisement.getPostingDate());
            jobAdvertisementDto.setApplicationDeadline(jobAdvertisement.getApplicationDeadline());
            jobAdvertisementDto.setOpenPositionNumber(jobAdvertisement.getOpenPositionNumber());

            jobAdvertisementDtoList.add(jobAdvertisementDto);
        }

        return new SuccessDataResult<List<JobAdvertisementDto>>("İş ilanları",jobAdvertisementDtoList);
    }
}
