/**
 * 
 */
package b8.ra291512.foundation.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import b8.ra291512.foundation.bank.entity.Account;

/**
 * @author Ravi
 *
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public List<Account> findByAccountType(String accountType);
	
	public Account findByAccountId(int accountId);
	
}
