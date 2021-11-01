package org.ssor.boss.core.transfer;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.ssor.boss.core.entity.AccountEntity;
import org.ssor.boss.core.entity.AccountInfo;
import org.ssor.boss.core.entity.AccountType;

/**
 * Provides the secure account information.
 *
 * @author John Christman
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SecureAccountDetails {
	private long id;
	private AccountType type;
	private int branch;

	@JsonUnwrapped
	private AccountInfo info;

	/**
	 * Default constructs the secure account details.
	 */
	public SecureAccountDetails() {
		id = 0;
		type = AccountType.UNDEFINED;
		branch = 0;
		info = null;
	}

	/**
	 * Creates the secure account details from an account entity.
	 *
	 * @param account The account entity to create the secure details from.
	 * @throws NullPointerException If the account entity is null.
	 */
	public SecureAccountDetails(@NonNull AccountEntity account) throws NullPointerException {
		id = account.getId();
		type = account.getType();
		branch = account.getBranch();
		info = account.getInfo();
	}
}
