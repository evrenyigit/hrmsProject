package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.core.utilities.Result;
import com.example.hrms.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
	boolean existsByEmail(String email);

//	User findByEmailAndVerifyCode(String email, String code);
}
