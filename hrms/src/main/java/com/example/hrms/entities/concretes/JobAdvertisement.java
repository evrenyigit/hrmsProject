package com.example.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name ="job_advertisement")
public class JobAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_advertisement_id")
    private Integer id;
    @Column(name = "job_description",nullable = false)
    private String jobDescription;
    @Column(name = "min_salary")
    private Integer minSalary;
    @Column(name = "max_salary")
    private Integer maxSalary;
    @Column(name = "open_position_number",nullable = false)
    private Integer openPositionNumber;
    @Column(name = "posting_date")
    private Date postingDate;
    @Column(name = "application_deadline")
    private Date applicationDeadline;
    @Column(name= "status",nullable = false)
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name="job_title_id")
    private JobTitle jobTitle;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name="employer_id")
    private Employer employer;
}
