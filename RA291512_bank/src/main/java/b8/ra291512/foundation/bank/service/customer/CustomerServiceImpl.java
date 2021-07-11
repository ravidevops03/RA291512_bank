/**
 * 
 */
package b8.ra291512.foundation.bank.service.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b8.ra291512.foundation.bank.dao.CustomerRepository;
import b8.ra291512.foundation.bank.entity.Customer;

/**
 * @author Ravi
 * 
 */

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer getCustomerByName(String name) {
		return customerRepository.findByCustName(name);
	}
	
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}	

}
