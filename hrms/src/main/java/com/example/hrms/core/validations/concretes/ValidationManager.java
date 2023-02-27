package com.example.hrms.core.validations.concretes;

import com.example.hrms.core.utilities.ErrorResult;
import com.example.hrms.dataAccess.abstracts.EmployerDao;
import com.example.hrms.dataAccess.abstracts.PeopleDetailDao;
import com.example.hrms.entities.concretes.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.core.utilities.Result;
import com.example.hrms.core.utilities.SuccessResult;
import com.example.hrms.core.validations.abstracts.ValidationService;
import com.example.hrms.core.verifications.abstracts.VerificationCodeService;
import com.example.hrms.dataAccess.abstracts.CandidateDao;
import com.example.hrms.dataAccess.abstracts.UserDao;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class ValidationManager implements ValidationService {

    private static final String emailPattern = "^[a-z\\d\\.-]+@[a-z\\d-]+\\.[a-z]{2,8}$";
    private CandidateDao candidateDao;
    private UserDao userDao;
    private PeopleDetailDao peopleDetailDao;
    private EmployerDao employerDao;

    @Autowired
    public ValidationManager(CandidateDao candidateDao, UserDao userDao, PeopleDetailDao peopleDetailDao,
                             EmployerDao employerDao) {
        this.candidateDao = candidateDao;
        this.userDao = userDao;
        this.peopleDetailDao = peopleDetailDao;
        this.employerDao = employerDao;
    }

    @Override
    public boolean isPasswordRepeatCorrectly(User user) {
        if (user.getPassword().equals(user.getRepeatPassword()))
            return true;
        else
            return false;
    }

    @Override
    public Result isAllSpaceFullCandidate(Candidate candidate) {
        if (candidate == null || ObjectUtils.isEmpty(candidate)) {
            return new ErrorResult("Candidate cannot be empty.");
        }
        else{
            return isAllSpaceFullPeopleDetail(candidate.getPeopleDetail());
        }
    }

    @Override
    public Result isAllSpaceFullEmployee(Employee employee) {
        if(employee == null || ObjectUtils.isEmpty(employee)){
            return new ErrorResult("Employee cannot be empty.");
        }
        else {
            return isAllSpaceFullPeopleDetail(employee.getPeopleDetail());
        }

    }

    @Override
    public Result isAllSpaceFullPeopleDetail(PeopleDetail peopleDetail) {
        if (peopleDetail == null || ObjectUtils.isEmpty(peopleDetail)) {
            return new ErrorResult("People detail boş olamaz.");
        } else if (peopleDetail.getTcNo() == null || peopleDetail.getTcNo().isEmpty()) {
            return new ErrorResult("Tc kimlik numarası boş olamaz.");
        } else if (peopleDetail.getTcNo().length() != 11) {
            return new ErrorResult("Geçerli Tc no değildir.");
        } else if (peopleDetail.getBirthOfYear() == 0) {
            return new ErrorResult("Dogum tarihi boş olamaz.");
        } else if (peopleDetail.getFirstName() == null || peopleDetail.getFirstName().isEmpty()) {
            return new ErrorResult("First name boş olamaz.");
        }else if (peopleDetail.getFirstName().length()<3) {
            return new ErrorResult("First name 2 haneden büyük olmalıdır.");
        } else if (peopleDetail.getLastName() == null || peopleDetail.getLastName().isEmpty()) {
            return new ErrorResult("Last name boş olamaz.");
        }else if (peopleDetail.getLastName().length()<2) {
            return new ErrorResult("Last name 1 haneden büyük olmalıdır.");
        }else if (!isIdentityNumberUsed(peopleDetail.getTcNo())) {
            return new ErrorResult("bu tc no kullanımda.");
        }else{
            return isAllSpaceFullUser(peopleDetail.getUser());
        }
    }

    @Override
    public boolean isIdentityNumberUsed(String tcNo) {
        if (peopleDetailDao.existsByTcNo(tcNo))
            return false;
        else
            return true;
    }

    @Override
    public boolean isEmailUsed(String email) {
        if (userDao.existsByEmail(email))
            return false;
        else
            return true;
    }

    @Override
    public Result isAllSpaceFullEmployer(Employer employer) {
        if (employer == null || ObjectUtils.isEmpty(employer))
            return new ErrorResult("Employer boş olamaz.");
    	else if (employer.getCompanyName() == null || employer.getCompanyName().isEmpty())
    		return new ErrorResult("Company name cannot be empty.");
    	else if (employer.getWebAddress() == null || employer.getWebAddress().isEmpty())
    		return new ErrorResult("Web address cannot be empty.");
    	else if (employer.getPhoneNumber() == null || employer.getPhoneNumber().isEmpty())
    		return new ErrorResult("Phone number cannot be empty.");
    	else if (!domainControlForEmployer(employer.getUser().getEmail(), employer.getWebAddress()))
    		return new ErrorResult("Lütfen web adresiniz ile aynı domaine sahip bir email adresi girin.");
        else
            return isAllSpaceFullUser(employer.getUser());
    }


    @Override
    public Result isAllSpaceFullUser(User user) {
        Pattern pattern = Pattern.compile(emailPattern);
        if (user == null || ObjectUtils.isEmpty(user)) {
            return new ErrorResult("User boş olamaz.");
        } else if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return new ErrorResult("Email boş olamaz.");
        } else if (!pattern.matcher(user.getEmail()).matches()) {
            return new ErrorResult("Mailiniz email formatında olmalıdır.");
        }else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return new ErrorResult("Password boş olamaz.");
        }else if (user.getPassword().length()<8) {
            return new ErrorResult("Password en az 8 haneli olmalıdır.");
        }else if (!isEmailUsed(user.getEmail())) {
            return new ErrorResult("bu email kullanılıyor");
        }else if (user.getRepeatPassword() == null || user.getRepeatPassword().isEmpty()) {
            return new ErrorResult("Password boş olamaz.");
        }else if(!isPasswordRepeatCorrectly(user)) {
            return new ErrorResult("Parola eşleşmiyor");
        }else{
            return new SuccessResult("User için bütün alanlar girildi.");
        }
    }

    @Override
    public Result checkIfRealPerson(String firstName, String lastName, String tcNo, int yearOfBirth) {
        return new SuccessResult("Mernis validation is success.");
    }

	@Override
	public boolean domainControlForEmployer(String email, String webAddress) {
		String[] domain = email.split("@");
		if(domain[1].equals(webAddress))
			return true;
		return false;
	}


}
