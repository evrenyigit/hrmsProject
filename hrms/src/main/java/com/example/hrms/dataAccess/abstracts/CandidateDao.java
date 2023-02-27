package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hrms.entities.concretes.Candidate;

@Repository
public interface CandidateDao extends JpaRepository<Candidate,Integer>{

    // TODO : neden v.id yapmıştık söylemiştin ama unuttum kanki
    @Query(value = "SELECT v.id FROM candidates c, people_details p, verification_codes v,verification_code_candidates vc, users u " +
            "WHERE c.people_detail_id = p.id AND c.id = vc.candidate_id\n" +
            "AND v.id = vc.verification_code_id AND p.user_id = u.id AND u.email = :email AND v.code = :code ",nativeQuery = true) // TODO : burda :email :code dediğimiz neye refer etti ?
    Integer dogrulamaCandidate (@Param("email") String email,@Param("code") String code); // TODO : @Param'ın kullanımı ne burda internette baktım böyle bi kullanım görmedim.

}
