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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ahackannection.entity.user.Visitor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String status;
	
	@ManyToOne
	@JoinColumn(name="visitor_id", referencedColumnName = "id", nullable = false, unique = true)
	private Visitor visitor; 
	
	@Column
	private byte itemsContained;
	
	@Column
	private double sum;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.MERGE)
	@JsonManagedReference
	private List<OrderItem> itemList;
	
	@OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
	private Transaction transaction;
}
