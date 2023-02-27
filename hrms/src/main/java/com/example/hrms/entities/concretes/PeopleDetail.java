package com.example.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="people_details",schema = "hrms")
public class PeopleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="tc_no",unique = true,nullable = false)
    private String tcNo;

    @Column(name="birth_of_year",nullable = false)
    private int birthOfYear;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName="id")
    private User user;


    public PeopleDetail(int id, String firstName, String lastName, String tcNo, int birthOfYear, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tcNo = tcNo;
        this.birthOfYear = birthOfYear;
        this.user = user;
    }
}
