package com.ahackannection.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ahackannection.entity.user.Administrator;
import com.ahackannection.entity.user.Organiser;
import com.ahackannection.entity.user.Trainer;
import com.ahackannection.entity.user.Visitor;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_category", discriminatorType = DiscriminatorType.INTEGER)
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name = "role")
	private String role;
	
	@Column(name = "of_corporate_type")
	private boolean corporate;
	
	@Column(name = "status")
	private String userStatus;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "password")	
	private String password;	
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "second_name")
	private String secondName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "country")
	private String country;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "_user", cascade = CascadeType.ALL)
	private List <SupportRequest> requests;	
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "_user", cascade = CascadeType.ALL)
	//private List <Administrator> administrators;	
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "_user", cascade = CascadeType.ALL)
	//private List <Visitor> visitors;	
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "_user", cascade = CascadeType.ALL)
	//private List <Trainer> trainers;	
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "_user", cascade = CascadeType.ALL)
	//private List <Organiser> organisers;	
	
}
