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
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "submissions")
@Data
@NoArgsConstructor
public class Submission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String status;
	
	@ManyToOne
	@JsonBackReference(value = "json-visitor")
	@JoinColumn(name="visitor_id", referencedColumnName = "id", nullable = false, unique = true)
	private Visitor visitor;
	
	@ManyToOne
	@JsonBackReference(value = "json-contest")
	@JoinColumn(name="contest_id", referencedColumnName = "id", nullable = false, unique = true)
	private Contest contest;	

}
