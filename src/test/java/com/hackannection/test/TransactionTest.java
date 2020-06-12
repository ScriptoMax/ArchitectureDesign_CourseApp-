package com.hackannection.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahackannection.HackannectionApplication;
import com.ahackannection.entity.Transaction;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.TransactionService;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@Transactional
@NoArgsConstructor
class TransactionTest {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionService TransactionService;

	@Test
	void testSaveTransaction() {
		Transaction newTransaction = new Transaction();
		Transaction newTransactionRet = new Transaction(); 
		newTransaction.setStatus("Resolved");
		newTransaction.setPayerAccount("24562886357");
	    newTransaction.setRecipientAccount("43256829368"); 
		newTransaction.setMethod("Apple Pay");
		newTransaction.setBankDetails("none");
		newTransaction.setSum(30);		
		
		try {
			newTransactionRet = transactionService.saveTransaction(newTransaction);
			assertThat(newTransactionRet).isNotNull();	
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testFindTransactionById() {		
		Transaction transaction = transactionService.findTransactionById(11L);
		assertThat(transaction).isNotNull();
		assertEquals(transaction.getOrder().getSum(), 20);
	}
		
	@Test
	void testResolveTransaction() {
		Transaction transaction = TransactionService.findTransactionById(7L);
		Transaction transaction1 = new Transaction();
		assertThat(transaction).isNotNull();
		assertEquals(transaction.getStatus(), "Accepted");
		transaction.setStatus("Resolved");
		try {
			transaction1 = TransactionService.saveTransaction(transaction);
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		assertThat(transaction1).isNotNull();
		assertEquals(transaction1.getStatus(), "Resolved");	
	}	
	
	@Test
	void testFindResolvedTransactions() {
		List<Transaction> transactions = transactionService.findAllTransactions();
		assertThat(transactions).isNotNull().isNotEmpty();	
		
		List<Transaction> resolvedList = transactions.stream()
				.filter(x -> x.getStatus().equals("Resolved"))				
				.collect(Collectors.toList());
		
		assertThat(resolvedList).isNotNull().isNotEmpty();		
	}
	
	@Test
	void testFindTransactionsByPayerAccount() {
		List<Transaction> transactions = transactionService.findTransactionsByPayerAccount("52456289001");
		Transaction transaction = transactionService.findTransactionById(12L);
		assertThat(transactions).isNotNull().isNotEmpty();	
		assertThat(transactions.contains(transaction));
	}
	
	@Test
	void testFindTransactionsByRecipientAccount() {
		List<Transaction> transactions = transactionService.findTransactionsByPayerAccount("2539028563");
		Transaction transaction = transactionService.findTransactionById(13L);
		assertThat(transactions).isNotNull().isNotEmpty();	
		assertThat(transactions.contains(transaction));
	}
	
	@Test
	void testFindAllTransactions() {
		List<Transaction> transactions = transactionService.findAllTransactions();
		assertThat(transactions).isNotNull().isNotEmpty();		
	}
	
	@Test
	void testRemoveTransaction() {	
		assertThrows(NoSuchElementException.class, 
		() -> {
			transactionService.removeTransaction(10L);
			Transaction transaction;
			transaction = transactionService.findTransactionById(10L);			
		});
	}
	
	
}
