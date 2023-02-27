package com.example.hrms.core.validations.abstracts;

import com.example.hrms.core.utilities.Result;
import com.example.hrms.entities.concretes.*;

public interface ValidationService {
	
	boolean isPasswordRepeatCorrectly(User user);
	boolean isIdentityNumberUsed(String tcNo);
	boolean isEmailUsed(String email);
	Result isAllSpaceFullCandidate(Candidate candidate);
	Result isAllSpaceFullEmployee(Employee employee);
	Result isAllSpaceFullPeopleDetail(PeopleDetail peopleDetail);
	Result isAllSpaceFullEmployer(Employer employer);
	Result isAllSpaceFullUser(User user);
	Result checkIfRealPerson(String firstName, String lastName , String tcNo, int yearOfBirth);
	boolean domainControlForEmployer(String email, String webAddress);

}
