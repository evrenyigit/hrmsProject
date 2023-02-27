package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.EmployerService;
import com.example.hrms.core.utilities.DataResult;
import com.example.hrms.core.utilities.Result;
import com.example.hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/employers")
public class EmployersController {

    private EmployerService employerService;

    @Autowired
    public EmployersController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getall")
    public DataResult<List<Employer>> getAll() {
        return employerService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Employer employer) {
        Result result = employerService.add(employer);
        if (result.isSuccess()) return ResponseEntity.ok().body(result);
        else return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/verify")
    public Result verify(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        return employerService.verifyEmployer(email, code);
    }

}
