package com.example.hrms.business.concretes;

import com.example.hrms.business.abstracts.CityService;
import com.example.hrms.core.utilities.Result;
import com.example.hrms.core.utilities.SuccessResult;
import com.example.hrms.dataAccess.abstracts.CityDao;
import com.example.hrms.entities.concretes.City;
import org.springframework.stereotype.Service;

@Service
public class CityManager implements CityService {

    private CityDao cityDao;

    public CityManager(CityDao cityDao){
        this.cityDao = cityDao;
    }
    @Override

    public Result add(City city) {
        cityDao.save(city);
        return new SuccessResult("Şehir başarıyla eklendi");
    }
}
