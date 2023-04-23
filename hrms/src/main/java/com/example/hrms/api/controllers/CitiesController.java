package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.CityService;
import com.example.hrms.core.utilities.Result;
import com.example.hrms.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

    private CityService cityService;

    @Autowired
    public CitiesController(CityService cityService){
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody City city){
        Result result = cityService.add(city);
        if(result.isSuccess())
            return ResponseEntity.ok().body(result);
        else
            return ResponseEntity.badRequest().body(result);

    }

}
