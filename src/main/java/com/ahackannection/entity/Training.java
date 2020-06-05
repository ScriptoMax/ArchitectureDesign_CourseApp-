package com.ahackannection.entity;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ahackannection.entity.user.Trainer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Time;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trainings")
@Data
@NoArgsConstructor
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private String specialization;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="trainer_id", referencedColumnName = "id", nullable = false, unique = true)
	private Trainer trainer;
	
	@Column
	private String annotation;
	
	@Column
	private String language;
	
	@Column
	private float avgRate;
	
	@Column
	private int totalRates;
	
	@Column
	private int totalStudents;
	
	@Column
	private String duration;
	
	@Column
	private String prerequisites;
	
	@Column
	private boolean isFree;
	
	@Column
	private double price;
	
	@Column
	private byte complexLevel;
	
	@Column
	private boolean hasCertificateOption;	
	
	@OneToOne(mappedBy = "training", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private OrderItem item;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "training", cascade = CascadeType.ALL)
	private List <TrainingReview> reviews;
	
}
