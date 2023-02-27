package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hrms.entities.concretes.Employer;

@Repository
public interface EmployerDao extends JpaRepository<Employer,Integer>{

    @Query(value = "SELECT v.id FROM employers e,verification_codes v, verification_code_employers ve, users u " +
            "WHERE e.user_id = u.id AND e.id = ve.employer_id AND v.id = ve.verification_code_id\n" +
            "AND u.email = :email AND v.code = :code", nativeQuery = true)
    Integer dogrulamaEmployerMail(@Param("email") String email,@Param("code") String code);

    Employer findByUser_Email(String email);

}
