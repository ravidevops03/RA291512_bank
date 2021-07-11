/**
 * 
 */
package b8.ra291512.foundation.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import b8.ra291512.foundation.bank.dao.AccountRepository;
import b8.ra291512.foundation.bank.dao.CustomerRepository;
import b8.ra291512.foundation.bank.entity.Account;
import b8.ra291512.foundation.bank.entity.Customer;


/**
 * @author Ravi
 *
 */
public class DBInit implements CommandLineRunner {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Customer  raviKumar = new Customer();
		raviKumar.setCustName("Ravi Kumar");
		raviKumar.setCustStreet("Kings Inn Street");
		raviKumar.setCustCity("Dublin");
		raviKumar = customerRepository.save(raviKumar);
		
		Account esaverRKS = new Account();
		esaverRKS.setAccountId(10);
		esaverRKS.setAccountType("S");
		esaverRKS.setCustomer(raviKumar);
		esaverRKS.setBalance(500.00);
		accountRepository.save(esaverRKS);
		
		Account esaverRKC = new Account();
		esaverRKC.setAccountId(50);
		esaverRKC.setAccountType("C");
		esaverRKC.setCustomer(raviKumar);
		esaverRKC.setBalance(1500.00);
		accountRepository.save(esaverRKC);
		
		Customer  sk = new Customer();
		sk.setCustName("Shashi Kumar");
		sk.setCustStreet("Pimple Saudagar");
		sk.setCustCity("Pune");
		sk = customerRepository.save(sk);
		
		Account esaverSKS = new Account();
		esaverSKS.setAccountId(20);
		esaverSKS.setAccountType("S");
		esaverSKS.setCustomer(sk);
		esaverSKS.setBalance(800.00);
		accountRepository.save(esaverSKS);
		
		Account esaverSKC = new Account();
		esaverSKC.setAccountId(60);
		esaverSKC.setAccountType("C");
		esaverSKC.setCustomer(sk);
		esaverSKC.setBalance(1700.00);
		accountRepository.save(esaverSKC);
	}

}
