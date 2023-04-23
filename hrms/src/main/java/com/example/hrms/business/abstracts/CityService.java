package com.example.hrms.business.abstracts;

import com.example.hrms.core.utilities.Result;
import com.example.hrms.entities.concretes.City;

public interface CityService {

    Result add(City city);
}
