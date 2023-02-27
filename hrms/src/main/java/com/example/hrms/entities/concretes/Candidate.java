package com.example.hrms.entities.concretes;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="candidates", schema = "hrms")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "people_detail_id", referencedColumnName="id")
	private PeopleDetail peopleDetail;

	@ManyToMany
	@JoinTable(
			name = "verification_code_candidates",
			schema = "hrms",
			joinColumns = @JoinColumn(name = "candidate_id"), //candidate id
			inverseJoinColumns = @JoinColumn(name = "verification_code_id")// verif code id
	)
	private List<VerificationCode> verificationCodeList;

	public Candidate(Integer id, PeopleDetail peopleDetail, List<VerificationCode> verificationCodeList) {
		this.id = id;
		this.peopleDetail = peopleDetail;
		this.verificationCodeList = verificationCodeList;
	}
}
