package com.ahackannection.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String status;
	
	@Column
	private String payerAccount;
	
	@Column
	private String recipientAccount;
	
	@Column
	private String method;
		
	@Column
	private double sum;
	
	@Column
	private String bankDetails;
	
	@Column
	private Date resolvedOn;
	
	@OneToOne(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Order order;
	
}
