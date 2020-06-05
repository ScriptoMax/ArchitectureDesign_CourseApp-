package com.ahackannection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ahackannection.entity.user.Visitor;

import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
public class TrainingReview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private byte rate;
	
	@Column
	private String comment;
	
	@Column
	private Date postedOn;
	
	@ManyToOne
	@JoinColumn(name="training_id", referencedColumnName = "id", nullable = false, unique = true)
	private Training training;
	
	@ManyToOne
	@JoinColumn(name="visitor_id", referencedColumnName = "id", nullable = false, unique = true)
	private Visitor visitor;

}
