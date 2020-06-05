package com.ahackannection.entity.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ahackannection.entity.Training;
import com.ahackannection.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@DiscriminatorValue("3")
@NoArgsConstructor
public class Trainer extends User {
	
	@Column
	private int numOfTrainings;
	
	@Column
	private float teachExperience;	
	
	@Column
	private String specializations;
	
	@Column
	private String languages;
	
	@Column
	private String affiliation;
	
	@Column
	private String affiliated_status;
	
	@Column
	private float averageRate;
	
	@Column
	private String website;
	
	@Column
	private String account;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trainer", cascade = CascadeType.MERGE)
	@JsonManagedReference
	@Column
	private List<Training> trainings;
	 
	
}
