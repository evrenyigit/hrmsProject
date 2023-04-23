package com.example.hrms.entities.dtos;

import com.example.hrms.entities.concretes.Employer;
import com.example.hrms.entities.concretes.JobTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {

    private String jobTitleName; //jobTitleın sadece isim özelliği olduğu için DTO yapmadım.
    private String employerName; //employerın adını alcaksın
    private String jobDescription;
    private Date postingDate;
    private Date applicationDeadline;

    private Integer openPositionNumber;
}