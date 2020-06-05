package com.ahackannection.entity.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ahackannection.entity.SupportRequest;
import com.ahackannection.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@Table(name = "administrators")
@DiscriminatorValue("1")
@NoArgsConstructor
public class Administrator extends User {
	
	//@ManyToOne
	//@JoinColumn(name="user_id", referencedColumnName = "id", nullable = false, unique = true)
	//private User _user; 
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admin", cascade = CascadeType.ALL)
	private List <SupportRequest> requests;	

}
