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
class CustomerRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	@Order(1)
	void testFindCustomerByCustomerWhenNotEmpty() {
//		fail("Not yet implemented");
		Iterable<Customer> cust = customerRepository.findAll();
		assertThat(cust).isNotEmpty();
	}

	/**
	 * Test method for {@link b8.ra291512.foundation.bank.dao.CustomerRepository#findByCustName(java.lang.String)}.
	 */
	@Test
	@Order(2)
	void testFindByCustName() {
//		fail("Not yet implemented");
		Customer custFound = customerRepository.findByCustName("Ravi Kumar");
		assertEquals("Ravi Kumar", custFound.getCustName());
	}

	/**
	 * Test method for {@link b8.ra291512.foundation.bank.dao.CustomerRepository#findByCustCity(java.lang.String)}.
	 */
	@Test
	@Order(3)
	void testFindByCustCity() {
//		fail("Not yet implemented");
		List <Customer> custFound = customerRepository.findByCustCity("Dublin");
		assertEquals(1, custFound.size());
	}

	/**
	 * Test method for {@link org.springframework.data.repository.CrudRepository#save(S)}.
	 */
	@Test
	@Order(4)
	void testSave() {
//		fail("Not yet implemented");
		Customer  nitinKumar = new Customer();
		nitinKumar.setCustName("Nitin Kumar");
		nitinKumar.setCustStreet("Madharpur");
		nitinKumar.setCustCity("Hyderbad");
		nitinKumar = customerRepository.save(nitinKumar);
		
		Customer custFound = customerRepository.findByCustName("Nitin Kumar");
		assertEquals("Nitin Kumar", custFound.getCustName());
	}

	/**
	 * Test method for {@link org.springframework.data.repository.CrudRepository#findById(java.lang.Object)}.
	 */
	@Test
	@Order(5)
	void testFindById() {
//		fail("Not yet implemented");
		
		List<Customer> allCustFound = customerRepository.findAll();
		Optional<Customer> custFound = customerRepository.findById(allCustFound.get(0).getId());
		assertThat(custFound).contains(allCustFound.get(0));
		
	}

	/**
	 * Test method for {@link org.springframework.data.repository.CrudRepository#findAll()}.
	 */
	@Test
	@Order(6)
	void testFindAll() {
//		fail("Not yet implemented");
		List<Customer> custFound = customerRepository.findAll();
		assertEquals(2, custFound.size());
	}

	/**
	 * Test method for {@link org.springframework.data.repository.CrudRepository#deleteById(java.lang.Object)}.
	 */
	@Test
	@Order(7)
	void testDeleteById() {
//		fail("Not yet implemented");
		List<Customer> allCustFound = customerRepository.findAll();
		customerRepository.deleteById(allCustFound.get(0).getId());
		Optional<Customer> custFound = customerRepository.findById(allCustFound.get(0).getId());
		assertThat(custFound).isEmpty();
	}

	/**
	 * Test method for {@link org.springframework.data.repository.CrudRepository#deleteAll()}.
	 */
	@Test
	@Order(8)
	void testDeleteAll() {
//		fail("Not yet implemented");
//		customerRepository.deleteAll();
//		assertThat(customerRepository.findAll()).isEmpty();
	}

}
