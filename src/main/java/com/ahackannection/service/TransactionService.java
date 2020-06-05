package com.ahackannection.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.Transaction;
import com.ahackannection.exception.ValidationException;

import java.sql.Date;

@Service
@Component
public interface TransactionService {
	
	Transaction saveTransaction(Transaction transaction) throws ValidationException;
    
	void removeTransaction(Long transactionId);
	
	void resolveTransaction(Long transactionId);
	
	Transaction findTransactionById(Long transactionId);
	
	List<Transaction> findTransactionsByPayerAccount(String payerAccount);
	
	List<Transaction> findTransactionsByRecipientAccount(String recipientAccount);
	
	List<Transaction> findTransactionsWithinPeriod(Date start, Date finish);
	
	List<Transaction> findAllTransactions();

}
