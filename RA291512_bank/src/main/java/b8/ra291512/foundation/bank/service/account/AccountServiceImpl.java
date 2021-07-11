/**
 * 
 */
package b8.ra291512.foundation.bank.service.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import b8.ra291512.foundation.bank.dao.AccountRepository;
import b8.ra291512.foundation.bank.dao.CustomerRepository;
import b8.ra291512.foundation.bank.entity.Account;
import b8.ra291512.foundation.bank.entity.Customer;
import b8.ra291512.foundation.bank.exception.ResourceNotFoundException;
import b8.ra291512.foundation.bank.pojo.Transaction;
import b8.ra291512.foundation.bank.util.BankConstants;

/**
 * @author Ravi
 *
 */

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Account getAccountByAccountId(int accountId) {
		return accountRepository.findByAccountId(accountId);
	}

	public List<Account> getAllAccount() {
		return accountRepository.findAll();
	}

	public String transferFunds(Transaction transactionAcct) {

		Account fromAccountDetails = null;
		Account toAccountDetails = null;

		System.out.println("From Account ::::" + transactionAcct.getFromAcct());
		System.out.println("To Account ::::" + transactionAcct.getToAcct());
		System.out.println("Transactiom amt ::::" + transactionAcct.getTransactionAmt());

		if ((!(transactionAcct.getFromAcct() != 0) && isAccountExist(transactionAcct.getFromAcct()))) {
			return BankConstants.PAYER_MSG;
		} else if ((!(transactionAcct.getToAcct() != 0) && isAccountExist(transactionAcct.getToAcct()))) {
			return BankConstants.BENEFICIARY_MSG;
		}

		fromAccountDetails = getAccountByAccountId(transactionAcct.getFromAcct());

		if (fromAccountDetails.getBalance() > transactionAcct.getTransactionAmt()) {
			double currPayerBal = fromAccountDetails.getBalance() - transactionAcct.getTransactionAmt();

			// Update the currPayerBal details in from acct

			fromAccountDetails.setBalance(currPayerBal);
			accountRepository.save(fromAccountDetails);

			toAccountDetails = accountRepository.findByAccountId(transactionAcct.getToAcct());

			double currBanBal = toAccountDetails.getBalance() + transactionAcct.getTransactionAmt();

			// Update the currBanBal details in To acct
			toAccountDetails.setBalance(currBanBal);
			accountRepository.save(toAccountDetails);

			return BankConstants.SUCCESS;

		} else {
			return BankConstants.INSUFF_FUND;
		}
	}

	public boolean isAccountExist(int accountId) {
		boolean isAcctExist = false;
		Account accountDetails = null;
		if (!(accountId == 0)) {
			accountDetails = accountRepository.findByAccountId(accountId);

			if (accountDetails == null) {
				return isAcctExist = false;
			} else {
				return isAcctExist = true;
			}
		}
		return isAcctExist;
	}

	public Account saveAccountdetails(Integer customerId, Account account) {

//		return customerRepository.findById(Long.valueOf(customerId)).map(customer -> {
//			account.setCustomer(customer);
//			return accountRepository.save(account);
//			
//		}).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));

		Optional<Customer> cust = customerRepository.findById(Long.valueOf(customerId));

		if (cust != null) {
			account.setCustomer(new Customer(cust.get().getId(),cust.get().getCustName(),cust.get().getCustStreet(),cust.get().getCustCity()) );
			return accountRepository.save(account);
		} else {
			throw new ResourceNotFoundException("Customer [customerId=" + customerId + "] can't be found");
		}
	}
}
