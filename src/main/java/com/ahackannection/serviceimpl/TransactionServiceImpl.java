package com.ahackannection.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.Transaction;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.TransactionRepository;
import com.ahackannection.service.TransactionService;
import com.google.common.collect.Lists;

import java.sql.Date;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private final TransactionRepository transactionRepository;
	
	private void validateTransaction(Transaction transaction) throws ValidationException {
		if(transaction == null) {
			throw new ValidationException("Object created is null");
		}		
	} 	
	
	public Transaction saveTransaction(Transaction transaction) throws ValidationException {
		validateTransaction(transaction);
		Transaction validTransaction = transactionRepository.save(transaction);
		return validTransaction;	
	}
	
	public void resolveTransaction(Long transactionId) {
		Transaction req = transactionRepository.findById(transactionId).get();
		req.setStatus("RESOLVED");
		transactionRepository.save(req);			
	}
	
	public void removeTransaction(Long transactionId) {
		transactionRepository.deleteById(transactionId);
	}
	
	public Transaction findTransactionById(Long transactionId) {		
		Transaction transaction = transactionRepository.findById(transactionId).get();
		
		return transaction; 
	}
	
	public List<Transaction> findTransactionsByPayerAccount(String payerAccount) {		
		List<Transaction> transactionList = Lists.newArrayList(transactionRepository.findAll());
		
		return transactionList.stream()
				.filter(x -> x.getPayerAccount().equals(payerAccount))
				.collect(Collectors.toList());
	}
	
	public List<Transaction> findTransactionsByRecipientAccount(String recipAccount) {		
		List<Transaction> transactionList = Lists.newArrayList(transactionRepository.findAll());
		
		return transactionList.stream()
				.filter(x -> x.getRecipientAccount().equals(recipAccount))
				.collect(Collectors.toList());
	}
	
	public List<Transaction> findTransactionsWithinPeriod(Date start, Date finish) {		
		List<Transaction> transactionList = Lists.newArrayList(transactionRepository.findAll());
		
		return transactionList.stream()
				.filter(x -> x.getResolvedOn().after(start) && x.getResolvedOn().before(finish))
				.collect(Collectors.toList());
	}
	
	public List<Transaction> findAllTransactions() {
		
		List<Transaction> transactionList = Lists.newArrayList(transactionRepository.findAll());
		
		return transactionList.stream()				
				.collect(Collectors.toList());
	}

}
