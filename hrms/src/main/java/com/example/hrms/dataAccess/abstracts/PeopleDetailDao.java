package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.concretes.PeopleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleDetailDao extends JpaRepository<PeopleDetail,Integer> {

    boolean existsByTcNo(String tcNo);

}
