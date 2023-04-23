package com.example.hrms.business.concretes;

import com.example.hrms.business.abstracts.JobAdvertisementService;
import com.example.hrms.core.utilities.*;
import com.example.hrms.dataAccess.abstracts.JobAdvertisementDao;
import com.example.hrms.entities.concretes.JobAdvertisement;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

    private JobAdvertisementDao jobAdvertisementDao;

    public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
        this.jobAdvertisementDao = jobAdvertisementDao;
    }

    @Override
    public List<JobAdvertisement> findAllJobAdvertisement() {
        return jobAdvertisementDao.findAllByIsActiveTrue();
    }

    @Override
    public List<JobAdvertisement> getAllSortPosting() {
        Sort sort = Sort.by(Sort.Direction.ASC,"postingDate");
        return jobAdvertisementDao.findAllByIsActiveTrue(sort);
    }

    @Override
    public List<JobAdvertisement> findAllByEmployer_EmployerId(Integer employerId) {
        return jobAdvertisementDao.findAllByIsActiveTrueAndEmployer_Id(employerId);
    }

    @Override
    public Result updateJobAdvertisementStatus(Integer id, boolean isActive) {
        JobAdvertisement jobAdvertisement = jobAdvertisementDao.getById(id);

        if(jobAdvertisement == null)
            throw new EntityNotFoundException("Job advertisement not found with this id");

        jobAdvertisement.setIsActive(isActive);
        jobAdvertisementDao.save(jobAdvertisement);
        return new SuccessResult("İş ilanının durumu değiştirildi.");
    }

    @Override
    public Result add(JobAdvertisement jobAdvertisement) { // TODO : burayı validationService'e alabilirsin.
        if(jobAdvertisement.getJobTitle()==null)
            return new ErrorResult("Job Title boş olamaz.");
        if(jobAdvertisement.getJobDescription()==null || jobAdvertisement.getJobDescription().isEmpty())
            return new ErrorResult("İş açıklaması boş olamaz");
        if(jobAdvertisement.getCity()==null)
            return new ErrorResult("Şehir bilgisi boş olamaz");
        if(jobAdvertisement.getOpenPositionNumber()==null || jobAdvertisement.getOpenPositionNumber()==0)
            return new ErrorResult("Açık pozisyon adedi boş olamaz");
        jobAdvertisementDao.save(jobAdvertisement);
        return new SuccessResult("İş ilanı başarıyla tanımlandı.");
    }
}
