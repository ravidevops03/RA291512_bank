/**
 * 
 */
package b8.ra291512.foundation.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the Customer.
 * 
 * Entity object that represents a Customer Details.
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
@Table(name = "CUSTOMER")
public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerId")
    private Long id;
	
	@Column(name = "custName")
	@NotNull
	private String custName;
	
	@Column(name = "custStreet")
	private String custStreet;
	
	@Column(name = "custCity")
	private String custCity;
	
	public Customer(String custName, String custStreet, String custCity) {
		this.custName = custName;
		this.custStreet = custStreet;
		this.custCity = custCity;
	}

}
