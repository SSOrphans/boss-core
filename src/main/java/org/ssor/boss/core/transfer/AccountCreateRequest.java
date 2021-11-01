package org.ssor.boss.core.transfer;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.ssor.boss.core.entity.AccountInfo;
import org.ssor.boss.core.entity.AccountType;
import java.io.Serializable;

/**
 * Provides an object that can be used for requesting the creation of an account.
 * <p>
 * Many of the properties listed here do not actually need to be filled out, at least from the perspective of the public
 * API, though for the administration API all properties should be provided.
 *
 * @author John Christman
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class AccountCreateRequest implements Serializable {
	private int[] userIds;
	private int accountType;
	private int branchId;

	@JsonUnwrapped
	private AccountInfo info;

	/**
	 * Default constructs the request with valid data.
	 */
	public AccountCreateRequest()
	{
		userIds = new int[0];
		accountType = AccountType.UNDEFINED.ordinal();
		branchId = 1;
		info = new AccountInfo();
	}
}
