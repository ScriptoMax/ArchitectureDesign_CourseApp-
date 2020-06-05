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

import com.ahackannection.entity.Submission;
import com.ahackannection.entity.TrainingReview;
import com.ahackannection.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@Table(name = "visitors")
@DiscriminatorValue("4")
@NoArgsConstructor
public class Visitor extends User {
	
	@Column
	private String city;
	
	@Column
	private String contestBackground;
	
	@Column
	private String portfolio;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "visitor", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "json-visitor")
	private List<Submission> submissions;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "visitor", cascade = CascadeType.ALL)
	private List<TrainingReview> reviews;
	
}
