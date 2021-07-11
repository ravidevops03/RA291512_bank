/**
 * 
 */
package b8.ra291512.foundation.bank.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import b8.ra291512.foundation.bank.entity.Account;
import b8.ra291512.foundation.bank.entity.Customer;

/**
 * @author Ravi
 *
 */

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class AccountRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	@Order(1)
	void testFindAccountByAccountIdWhenNotEmpty() {
//		fail("Not yet implemented");
		Iterable<Account> acct = accountRepository.findAll();
		assertThat(acct).isNotEmpty();
	}

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.dao.AccountRepository#findByAccountType(java.lang.String)}.
	 */
	@Test
	@Order(2)
	void testFindByAccountType() {
//		fail("Not yet implemented");
		Account acct1 = new Account(10, new Customer("Ravi Kumar", "Kings Inn Street", "Dublin"), "S", 1500);
		
		List<Account> acctFound = accountRepository.findByAccountType(acct1.getAccountType());

		assertEquals(2, acctFound.size());

	}

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.dao.AccountRepository#findByAccountId(int)}.
	 */
	@Test
	@Order(3)
	void testFindByAccountId() {
//		fail("Not yet implemented");
		Account acct1 = new Account(10, new Customer("Ravi Kumar", "Kings Inn Street", "Dublin"), "S", 1500);

		Account acctFound = accountRepository.findByAccountId(10);

		assertEquals(acctFound.getAccountId(), acct1.getAccountId());
	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#save(S)}.
	 */
	@Test
	@Order(4)
	void testSave() {
//		fail("Not yet implemented");		
		Customer raviKumar = customerRepository.findByCustName("Ravi Kumar");
		Account esaverRKS = new Account();
		esaverRKS.setAccountId(100);
		esaverRKS.setAccountType("S");
		esaverRKS.setCustomer(raviKumar);
		esaverRKS.setBalance(500.00);
		accountRepository.save(esaverRKS);

		Account acctFound = accountRepository.findByAccountId(esaverRKS.getAccountId());

		assertEquals(acctFound.getAccountId(), esaverRKS.getAccountId());

	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#findById(java.lang.Object)}.
	 */
	@Test
	@Order(5)
	void testFindById() {
//		fail("Not yet implemented");
		List<Account> allAcctFound = accountRepository.findAll();
		Optional<Account> acctFound = accountRepository.findById(allAcctFound.get(0).getId());
		assertThat(acctFound).contains(allAcctFound.get(0));
	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#findAll()}.
	 */
	@Test
	@Order(6)
	void testFindAll() {
//		fail("Not yet implemented");		
		List<Account> acctFound = accountRepository.findAll();
		assertEquals(4, acctFound.size());
	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#deleteById(java.lang.Object)}.
	 */
	@Test
	@Order(7)
	void testDeleteById() {
//		fail("Not yet implemented");
		List<Account> allAcctFound = accountRepository.findAll();
		accountRepository.deleteById(allAcctFound.get(0).getId());
		Optional<Account> acctFound = accountRepository.findById(allAcctFound.get(0).getId());
		assertThat(acctFound).isEmpty();

	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#deleteAll()}.
	 */
	@Test
	@Order(8)
	void testDeleteAll() {
//		fail("Not yet implemented");
		accountRepository.deleteAll();
		assertThat(accountRepository.findAll()).isEmpty();
	}

}
