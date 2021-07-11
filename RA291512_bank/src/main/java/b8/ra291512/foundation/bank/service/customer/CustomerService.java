/**
 * 
 */
package b8.ra291512.foundation.bank.service.customer;

import java.util.List;

import b8.ra291512.foundation.bank.entity.Customer;

/**
 * @author Ravi
 *
 */
public interface CustomerService {
	Customer getCustomerByName(String name);
	List <Customer> getAllCustomer();
}
