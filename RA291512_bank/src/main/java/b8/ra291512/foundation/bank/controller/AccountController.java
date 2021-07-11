/**
 * 
 */
package b8.ra291512.foundation.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import b8.ra291512.foundation.bank.dao.AccountRepository;
import b8.ra291512.foundation.bank.dao.CustomerRepository;
import b8.ra291512.foundation.bank.entity.Account;
import b8.ra291512.foundation.bank.exception.ResourceNotFoundException;
import b8.ra291512.foundation.bank.pojo.Transaction;
import b8.ra291512.foundation.bank.service.account.AccountServiceImpl;
import b8.ra291512.foundation.bank.util.BankConstants;

/**
 * @author Ravi
 *
 */
@RestController
@RequestMapping("/account/v1")
public class AccountController {

	@Autowired
	private AccountServiceImpl accountServiceImpl;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/")
	public String getWelcomeMessage2() {
		return "Welcome to REST end point Account Service";
	}

	@GetMapping("/getallaccountdetails")
	public List<Account> getAllAccountdetails() {
		return accountServiceImpl.getAllAccount();
	}

	@GetMapping("/getBalanceOf/{accountId}/")
	public Account getAccountByAccountId(@PathVariable int accountId) {

		Account getBalanceOf = accountServiceImpl.getAccountByAccountId(accountId);

		if (getBalanceOf == null) {
			throw new ResourceNotFoundException("Account [accountId=" + accountId + "] can't be found");
		}
		return getBalanceOf;
	}

	@PostMapping(value = "/customers/{customerId}/accounts")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Account save(@PathVariable Integer customerId, @RequestBody Account account) {

		Account accDetails = null;
		try {
			accDetails = accountServiceImpl.saveAccountdetails(customerId, account);
		} catch (Exception ex) {
			throw new ResourceNotFoundException("Customer [customerId=" + customerId + "] can't be found");
		}

		return accDetails;

//		
//		return customerRepository.findById(Long.valueOf(customerId)).map(customer -> {
//			account.setCustomer(customer);
//			return accountRepository.save(account);
//			
//		}).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));

	}

	@PutMapping(value = "/transaction", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> transferFunds(@RequestBody Transaction transactionAcct) {
		String response = null;

		response = accountServiceImpl.transferFunds(transactionAcct);

		if (response != null && ((response.equalsIgnoreCase(BankConstants.PAYER_MSG))
				|| (response.equalsIgnoreCase(BankConstants.BENEFICIARY_MSG)))) {
			return new ResponseEntity<String>(BankConstants.ID_MISATCH, HttpStatus.NOT_ACCEPTABLE);
		} else if (response.equalsIgnoreCase(BankConstants.INSUFF_FUND)) {
			return new ResponseEntity<String>(BankConstants.INSUFF_FUND, HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<String>(BankConstants.SUCCESS, HttpStatus.OK);
		}
	}

}
