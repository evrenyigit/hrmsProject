package com.example.hrms.business.concretes;

import com.example.hrms.business.abstracts.CandidateService;
import com.example.hrms.core.utilities.*;
import com.example.hrms.core.validations.abstracts.ValidationService;
import com.example.hrms.core.verifications.abstracts.VerificationCodeService;
import com.example.hrms.dataAccess.abstracts.CandidateDao;
import com.example.hrms.dataAccess.abstracts.PeopleDetailDao;
import com.example.hrms.dataAccess.abstracts.UserDao;
import com.example.hrms.dataAccess.abstracts.VerificationCodeDao;
import com.example.hrms.entities.concretes.Candidate;
import com.example.hrms.entities.concretes.PeopleDetail;
import com.example.hrms.entities.concretes.User;
import com.example.hrms.entities.concretes.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CandidateManager implements CandidateService {

    private ValidationService validationService;
    private CandidateDao candidateDao;
    private VerificationCodeService verificationCodeService;
    private UserDao userDao;
    private PeopleDetailDao peopleDetailDao;
    private VerificationCodeDao verificationCodeDao;

    @Autowired
    public CandidateManager(ValidationService validationService, CandidateDao candidateDao, VerificationCodeService verificationCodeService,
                            UserDao userDao,
                            PeopleDetailDao peopleDetailDao,VerificationCodeDao verificationCodeDao) {
        this.candidateDao = candidateDao;
        this.validationService = validationService;
        this.verificationCodeService = verificationCodeService;
        this.userDao = userDao;
        this.peopleDetailDao = peopleDetailDao;
        this.verificationCodeDao = verificationCodeDao;
    }

    @Override
    public Result add(Candidate candidate) {

        Result result = validationService.isAllSpaceFullCandidate(candidate);

        if (result.isSuccess()) {
                User user = candidate.getPeopleDetail().getUser();
                user = userDao.save(user);
                PeopleDetail peopleDetail = candidate.getPeopleDetail();
                peopleDetail = peopleDetailDao.save(peopleDetail);
                List<VerificationCode> verificationCodeList = new ArrayList<>();
                VerificationCode verificationCode = new VerificationCode(UUID.randomUUID().toString(),false,new Date());
                verificationCodeService.sendCode(candidate.getPeopleDetail().getUser().getEmail(),verificationCode.getCode());
                verificationCode = verificationCodeDao.save(verificationCode);
                verificationCodeList.add(verificationCode);
                candidate.setVerificationCodeList(verificationCodeList);
                candidate = candidateDao.save(candidate);
                result = new SuccessResult("İş arayan kaydı oluşturuldu. Lütfen e posta adresinize gönderilen linke tıklayarak üyeliğinzi doğrulayın.");
            }
            return result;
    }

    @Override
    public Result verifyCandidate(String email,String code) {
        Integer verify = candidateDao.dogrulamaCandidate(email, code);
    	if(verify==null)
    		return new ErrorResult("Doğrulama başarısız.");
        VerificationCode verificationCode = verificationCodeDao.findById(verify).get();
                if(verificationCode.isVerified()){
                    return new ErrorResult("Daha önce dogrulandı.");
                }else{
                    verificationCode.setVerified(true);
                    verificationCodeDao.save(verificationCode);
                }
    	return new SuccessResult("İş arayan emaili başarıyla doğrulandı. Giriş yapabilirsiniz.");
    }

    @Override
    public SuccessDataResult<List<Candidate>> getAll() {
        return new SuccessDataResult<List<Candidate>>("Candidates listed.", candidateDao.findAll());
    }

}
