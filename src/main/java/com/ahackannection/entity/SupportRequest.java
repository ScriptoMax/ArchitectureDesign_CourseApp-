package com.ahackannection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ahackannection.entity.user.Administrator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "requests")
@Data
@NoArgsConstructor
public class SupportRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id", nullable = false, unique = true)
	private User _user;
	
	@ManyToOne
	@JoinColumn(name="admin_id", referencedColumnName = "id", nullable = false, unique = true)
	private Administrator admin;
	
	@Column
	private String requestStatus;
	
	@Column
	private String topic;
	
	@Column
	private String body;
	
}
