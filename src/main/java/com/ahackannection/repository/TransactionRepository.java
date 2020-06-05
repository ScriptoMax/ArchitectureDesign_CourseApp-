package com.ahackannection.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahackannection.entity.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>  {
	
	Transaction findTransactionById(Long transactionId);
	
	List<Transaction> findTransactionsByPayerAccount(Long userId);
	
	List<Transaction> findTransactionsByRecipientAccount(Long userId);
	
}
