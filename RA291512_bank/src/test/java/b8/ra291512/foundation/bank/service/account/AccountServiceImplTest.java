/**
 * 
 */
package b8.ra291512.foundation.bank.service.account;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import b8.ra291512.foundation.bank.dao.AccountRepository;
import b8.ra291512.foundation.bank.entity.Account;
import b8.ra291512.foundation.bank.entity.Customer;

/**
 * @author Ravi
 *
 */
@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {

	@InjectMocks
	private AccountService accountService = (AccountService) new AccountServiceImpl();

	@Mock
	private AccountRepository accountRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.service.account.AccountServiceImpl#getAccountByAccountId(int)}.
	 */
	@Test
	void testGetAccountByAccountId() {
//		fail("Not yet implemented");

		Account acct = new Account(10, new Customer("Ravi Kumar", "Kings Inn Street", "Dublin"), "S", 1500);

		Mockito.when(accountRepository.findByAccountId(acct.getAccountId())).thenReturn(acct);

		Account foundAcct = accountService.getAccountByAccountId(10);

		assertEquals("S", foundAcct.getAccountType());
	}

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.service.account.AccountServiceImpl#getAllAccount()}.
	 */
	@Test
	void testGetAllAccount() {
//		fail("Not yet implemented");

		Mockito.when(accountRepository.findAll()).thenReturn(Stream// @formatter:off
				.of(new Account(10, new Customer("Ravi Kumar", "Kings Inn Street", "Dublin"), "S", 1500),
						new Account(20, new Customer("Shashi", "Pimple Saudagar", "Pune"), "C", 2500))
				.collect(Collectors.toList())
		// @formatter:on
		);
		assertEquals(2, ((AccountServiceImpl) accountService).getAllAccount().size());
	}
}
