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

import com.ahackannection.entity.Contest;
import com.ahackannection.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@DiscriminatorValue("2")
@NoArgsConstructor
public class Organiser extends User {
	
	@Column
	private String affiliation;
	
	@Column
	private String affiliated_status;

	@Column
	private int openContestNumber;
	
	@Column
	private int totalContestNumber;
	
	@Column
	private String website;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organiser", cascade = CascadeType.ALL)
	@JsonManagedReference
	@Column
	private List<Contest> contests;
	
	
}
