/**
 * 
 */
package b8.ra291512.foundation.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import b8.ra291512.foundation.bank.dao.CustomerRepository;
import b8.ra291512.foundation.bank.entity.Customer;

/**
 * @author Ravi
 *
 */

@RestController
@RequestMapping("/customer/v1")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping(value = "/addcustomer", consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Customer save(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	 }
	
	@GetMapping("/")
	public String getWelcomeMessage1() {
		return "Welcome to Customer REST end point";
	}
	
	@GetMapping(value="/findallcustomer", consumes = "application/json", produces = "application/json")
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();		
	}
}
