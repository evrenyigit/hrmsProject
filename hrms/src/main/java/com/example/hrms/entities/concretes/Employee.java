package com.example.hrms.entities.concretes;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="employees", schema = "hrms")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@OneToOne
	@JoinColumn(name = "people_detail_id", referencedColumnName="id")
	private PeopleDetail peopleDetail;


}