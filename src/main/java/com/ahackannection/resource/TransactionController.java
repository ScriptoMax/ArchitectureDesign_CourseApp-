package com.ahackannection.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahackannection.entity.Transaction;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.TransactionService;

import java.sql.Date;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {
	
	private final TransactionService transactionService;
	
	@PostMapping("/saveTransaction")
	@ResponseBody
	public Transaction saveTransaction(@RequestBody Transaction transaction) throws ValidationException {
		return transactionService.saveTransaction(transaction);
	}
	
	@PutMapping("/resolveTransaction")
	public void resolveTransaction(@RequestParam Long transactionId) {
		transactionService.resolveTransaction(transactionId);
	}
	
	@GetMapping("/findById")
	public Transaction findTransactionById(@RequestParam Long transactionId) {
		return transactionService.findTransactionById(transactionId);
	}
	
	@GetMapping("/findWithinPeriod")
	public List<Transaction> findTransactionsWithinPeriod(@RequestParam Date start, @RequestParam Date finish) {
		return transactionService.findTransactionsWithinPeriod(start, finish);
	}
	
	@GetMapping("/findByPayerAccount")
	public List<Transaction> findTransactionsByPayerAccount(@RequestParam String payerAccount) {
		return transactionService.findTransactionsByPayerAccount(payerAccount);
	}
	
	@GetMapping("/findByRecipientAccount")
	public List<Transaction> findTransactionsByRecipientAccount(@RequestParam String recipientAccount) {
		return transactionService.findTransactionsByRecipientAccount(recipientAccount);
	}
	
	@GetMapping("/findAllTransactions")
	public List<Transaction> findAllTransactions() {
		return transactionService.findAllTransactions();
	}
	
	@DeleteMapping("/removeTransaction/{transactionId}")
	public ResponseEntity<Void> removeTransaction(@PathVariable Long transactionId) {
		transactionService.removeTransaction(transactionId);
		return ResponseEntity.ok().build();
	}

}
