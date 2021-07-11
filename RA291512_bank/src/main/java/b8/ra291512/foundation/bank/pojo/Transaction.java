/**
 * 
 */
package b8.ra291512.foundation.bank.pojo;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Ravi
 *
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	
	private int transactionAmt;
	private int fromAcct;
	private int toAcct;

}
