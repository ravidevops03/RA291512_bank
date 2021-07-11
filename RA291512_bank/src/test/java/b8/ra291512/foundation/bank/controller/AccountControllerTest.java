/**
 * 
 */
package b8.ra291512.foundation.bank.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import b8.ra291512.foundation.bank.dao.AccountRepository;
import b8.ra291512.foundation.bank.dao.CustomerRepository;
import b8.ra291512.foundation.bank.entity.Account;
import b8.ra291512.foundation.bank.entity.Customer;
import b8.ra291512.foundation.bank.exception.ResourceNotFoundException;
import b8.ra291512.foundation.bank.pojo.Transaction;
import b8.ra291512.foundation.bank.service.account.AccountServiceImpl;
import b8.ra291512.foundation.bank.service.customer.CustomerServiceImpl;
import b8.ra291512.foundation.bank.util.BankConstants;
import b8.ra291512.foundation.bank.util.BankUtils;

/**
 * @author Ravi
 *
 */

@AutoConfigureMockMvc
@WebMvcTest(AccountController.class)
class AccountControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CustomerServiceImpl customerServiceImpl;

	@MockBean
	private AccountServiceImpl accountServiceImpl;

	@MockBean
	private CustomerRepository customerRepository;

	@MockBean
	private AccountRepository accountRepository;

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.controller.AccountController#getWelcomeMessage1()}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testGetWelcomeMessage1() throws Exception {
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get(BankConstants.ACCOUNT_API + "/")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultMessage = result.getResponse().getContentAsString();
		assertEquals("Welcome to REST end point Account Service", resultMessage);
	}

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.controller.AccountController#getAllAccountdetails()}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testGetAllAccountdetails() throws Exception {
		when(accountServiceImpl.getAllAccount()).thenReturn(Stream// @formatter:off
				.of(new Account(10, new Customer("Ravi Kumar", "Kings Inn Street", "Dublin"), "S", 1500),
						new Account(20, new Customer("Shashi", "Pimple Saudagar", "Pune"), "C", 2500))
				.collect(Collectors.toList()));

		mvc.perform(get(BankConstants.ACCOUNT_API + "/getallaccountdetails").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].accountId").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].customer.custName").value("Ravi Kumar"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

		verify(accountServiceImpl).getAllAccount();
	}

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.controller.AccountController#getAccountByAccountId(int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testGetAccountByAccountId() throws Exception {

		Account acct = new Account(10, new Customer("Ravi Kumar", "Kings Inn Street", "Dublin"), "S", 1500);

		when(accountServiceImpl.getAccountByAccountId(acct.getAccountId())).thenReturn(acct);

		mvc.perform(get(BankConstants.ACCOUNT_API + "/getBalanceOf/{accountId}/", 10)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.accountId").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.customer.custName").value("Ravi Kumar"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(5));

		verify(accountServiceImpl).getAccountByAccountId(10);
	}

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.controller.AccountController#save(java.lang.Integer, b8.ra291512.foundation.bank.entity.Account)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testSaveWhenCustomerExist() throws Exception {

		Account acct = new Account(10, new Customer((long) 100, "Ravi Kumar", "Kings Inn Street", "Dublin"), "S", 1500);

		when(accountServiceImpl.saveAccountdetails(100, acct)).thenReturn(acct);

		mvc.perform(post(BankConstants.ACCOUNT_API + "/customers/{customerId}/accounts", 100)
				.content(BankUtils.asJsonString(acct)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print());

	}
}
