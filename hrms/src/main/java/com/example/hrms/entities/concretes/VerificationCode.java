package com.example.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="verification_codes",schema = "hrms")
public class VerificationCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="is_verified")
	private boolean isVerified;
	
	@Column(name="verified_date")
	private Date verifiedDate;

	public VerificationCode(String code, boolean isVerified, Date verifiedDate) {
		this.code = code;
		this.isVerified = isVerified;
		this.verifiedDate = verifiedDate;
	}
}
