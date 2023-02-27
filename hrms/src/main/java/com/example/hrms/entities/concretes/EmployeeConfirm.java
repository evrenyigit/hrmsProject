package com.example.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="employee_confirms", schema = "hrms")
public class EmployeeConfirm {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private Integer id;
		
		@Column(name="employee_id")
		private Integer employeeId;
		
		@Column(name="is_confirmed")
		private boolean confirmed;
		
		@Column(name="confirmed_date")
		private Date confirmedDate;

		public EmployeeConfirm(Integer employeeId, boolean confirmed, Date confirmedDate){
			this.employeeId = employeeId;
			this.confirmed = confirmed;
			this.confirmedDate = confirmedDate;
		}

		public EmployeeConfirm() {

		}
}
