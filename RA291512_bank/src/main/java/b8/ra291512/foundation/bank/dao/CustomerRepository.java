/**
 * 
 */
package b8.ra291512.foundation.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import b8.ra291512.foundation.bank.entity.Customer;

/**
 * @author Ravi
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
		
	public Customer findByCustName(String custName);
	
	public List<Customer> findByCustCity(String custCity);
	
}
