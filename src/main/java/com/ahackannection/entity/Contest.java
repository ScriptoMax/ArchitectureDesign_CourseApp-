package com.ahackannection.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ahackannection.entity.user.Organiser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contests")
@Data
@NoArgsConstructor
public class Contest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private String specialization;	
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="organiser_id", referencedColumnName = "id", nullable = false, unique = true)
	private Organiser organiser;	
	
	@Column
	private String annotation;
		
	@Column
	private int quota;
	
	@Column
	private int numOfSubmissions;
	
	@Column
	private Date eventStart;
	
	@Column
	private Date eventFinish;
	
	@Column
	private String eventPlace;
	
	@Column
	private byte traineeLevel;
	
	@Column
	private String prerequisites;
	
	@Column
	private boolean isRegistrationOpen;	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contest", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "json-contest")
	private List<Submission> submissions;
}
