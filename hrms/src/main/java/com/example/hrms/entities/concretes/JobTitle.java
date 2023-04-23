package com.example.hrms.entities.concretes;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertisementList"})
@Data
@Entity
@Table(name="job_titles",schema = "hrms")
public class JobTitle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="job_name",nullable = false)
	private String jobName;

	//job advertisement, job title ile maplendi.
	@OneToMany(mappedBy = "jobTitle")
	private List<JobAdvertisement> jobAdvertisementList;
	
	public JobTitle(int id, String jobName) {
		this.id = id;
		this.jobName = jobName;
	}
	
	public JobTitle() {
		
	}

}
