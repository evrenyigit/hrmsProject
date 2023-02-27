package com.example.hrms.entities.concretes;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="employers",schema = "hrms")
public class Employer{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="web_site")
	private String webAddress;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToMany
	@JoinTable(
			name = "verification_code_employers",
			schema = "hrms",
			joinColumns = @JoinColumn(name = "employer_id"), //employer id
			inverseJoinColumns = @JoinColumn(name = "verification_code_id")// verif code id
	)
	private List<VerificationCode> verificationCodeList;

	@ManyToMany
	@JoinTable(
			name = "employee_confirm_employers",
			schema = "hrms",
			joinColumns = @JoinColumn(name = "employer_id"), // employer id
			inverseJoinColumns = @JoinColumn (name = "employee_confirm_id")
	)
	private List<EmployeeConfirm> employeeConfirmList;

	public Employer(int id,String companyName, String webAddress, String phoneNumber,User user,
					List<VerificationCode> verificationCodeList, List<EmployeeConfirm> employeeConfirmList) {
		this.id = id;
		this.companyName = companyName;
		this.webAddress = webAddress;
		this.phoneNumber = phoneNumber;
		this.user = user;
		this.verificationCodeList = verificationCodeList;
		this.employeeConfirmList = employeeConfirmList;
	}
	public Employer() {
		
	}
	
}
