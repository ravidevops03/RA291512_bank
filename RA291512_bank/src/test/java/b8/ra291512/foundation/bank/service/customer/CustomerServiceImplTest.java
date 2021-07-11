/**
 * 
 */
package b8.ra291512.foundation.bank.service.customer;

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
import b8.ra291512.foundation.bank.dao.CustomerRepository;
import b8.ra291512.foundation.bank.entity.Customer;

/**
 * 
 * @author Ravi
 *
 */
@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {

	@InjectMocks
	private CustomerService customerService = (CustomerService) new CustomerServiceImpl();

	@Mock
	private CustomerRepository customerRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.service.customer.CustomerServiceImpl#getCustomerByName(java.lang.String)}.
	 */
	@Test
	void testGetCustomerByName() {
//		fail("Not yet implemented");

		Customer customer = new Customer("Ravi Kumar", "Kings Inn Street", "Dublin");

		Mockito.when(customerRepository.findByCustName(customer.getCustName())).thenReturn(customer);

		Customer foundCust = customerService.getCustomerByName("Ravi Kumar");
		assertEquals("Dublin", foundCust.getCustCity());

	}

	/**
	 * Test method for
	 * {@link b8.ra291512.foundation.bank.service.customer.CustomerServiceImpl#getAllCustomer()}.
	 */
	@Test
	void testGetAllCustomer() {
//		fail("Not yet implemented");
		Mockito.when(customerRepository.findAll()).thenReturn(Stream // @formatter:off
				.of(new Customer("Ravi Kumar", "Kings Inn Street", "Dublin"),
						new Customer("Shashi", "Pimple Saudagar", "Pune"))
				.collect(Collectors.toList()));
		// @formatter:on

		assertEquals(2, customerService.getAllCustomer().size());

	}

}
