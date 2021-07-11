/**
 * 
 */
package b8.ra291512.foundation.bank.entity;

import java.util.Optional;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the account.
 * 
 * Entity object that represents a account Details.
 *  
 * @author Ravi
 *
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class Account {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	
	@Column(name = "accountId", length = 250)
    @NotNull
	private int accountId;
	
	@Access(AccessType.PROPERTY)
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@Column(name = "accounttype")
	@NotNull
	private String accountType;
	
	@Column(name = "balance", precision = 14, scale = 2, nullable=false)
	@NotNull
	private double balance;
	
	public Account(int accountId,Customer customer,String accountType,double balance) {
		this.accountId = accountId;
		this.customer = customer;
		this.accountType = accountType;
		this.balance = balance;
	}

//	/**
//	 * @param cust
//	 */
//	public void setCustomer(Optional<Customer> cust) {
//		// TODO Auto-generated method stub
//		this.cust =cust;
//	}
}
