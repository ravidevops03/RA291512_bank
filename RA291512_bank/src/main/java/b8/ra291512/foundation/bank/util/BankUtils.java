/**
 * 
 */
package b8.ra291512.foundation.bank.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Ravi
 *
 */
public class BankUtils {
	
	public static String asJsonString(final Object obj) {
		try {
			System.out.println(
					"********json message************" + new ObjectMapper().writeValueAsString(obj).toString());
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
