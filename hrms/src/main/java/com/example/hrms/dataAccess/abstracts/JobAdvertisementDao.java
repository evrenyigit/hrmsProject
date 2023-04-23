package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.concretes.JobAdvertisement;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement,Integer> {

    List<JobAdvertisement> findAllByIsActiveTrueAndEmployer_Id(Integer EmployerId);
    List<JobAdvertisement> findAllByIsActiveTrue(Sort sort);
    List<JobAdvertisement> findAllByIsActiveTrue();

}
