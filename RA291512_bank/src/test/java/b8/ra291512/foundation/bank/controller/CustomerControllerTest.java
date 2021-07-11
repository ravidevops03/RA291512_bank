/**
 * 
 */
package b8.ra291512.foundation.bank.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import b8.ra291512.foundation.bank.dao.AccountRepository;
import b8.ra291512.foundation.bank.dao.CustomerRepository;
import b8.ra291512.foundation.bank.entity.Customer;
import b8.ra291512.foundation.bank.service.customer.CustomerServiceImpl;
import b8.ra291512.foundation.bank.util.BankUtils;

/**
 * @author Ravi
 *
 */

//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CustomerServiceImpl customerServiceImpl;

	@MockBean
	private CustomerRepository customerRepository;

	@MockBean
	private AccountRepository accountRepository;

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.controller.CustomerController#getWelcomeMessage1()}.
	 * 
	 * @throws Exception
	 */
//	@Test
	void testGetWelcomeMessage1() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/customer/v1/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();
		String resultMessage = result.getResponse().getContentAsString();
		System.out.println("**************" + resultMessage);
		assertEquals("Welcome to Customer REST end point", resultMessage);
	}

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.controller.CustomerController#save(b8.ra291512.foundation.bank.entity.Customer)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testSave() throws Exception {

		Customer nitinKumar = new Customer();
		nitinKumar.setId((long) 1);
		nitinKumar.setCustName("Nitin Kumar");
		nitinKumar.setCustStreet("Madharpur");
		nitinKumar.setCustCity("Hyderbad");
		when(customerRepository.save(nitinKumar)).thenReturn(nitinKumar);

		mvc.perform(MockMvcRequestBuilders.post("/customer/v1/addcustomer").content(BankUtils.asJsonString(nitinKumar))
//			    		  new Customer("Nitin Kumar", "Madharpur","Hyderabad")))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isCreated()).andReturn();
//			      .andExpect(MockMvcResultMatchers.jsonPath("$.custName").exists());

	}

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.controller.CustomerController#getAllCustomers()}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testGetAllCustomers() throws Exception {

		when(customerRepository.findAll()).thenReturn(Stream // @formatter:off
				.of(new Customer("Ravi Kumar", "Kings Inn Street", "Dublin"),
						new Customer("Shashi", "Pimple Saudagar", "Pune"))
				.collect(Collectors.toList()));

		mvc.perform(get("/customer/v1/findallcustomer").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].custName").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

		verify(customerRepository).findAll();
	}	

}
