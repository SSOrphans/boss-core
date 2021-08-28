package org.ssor.boss.core.transfer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ssor.boss.core.entity.AccountEntity;
import java.util.Objects;

// TODO: fix this.
@Setter
@Getter
@NoArgsConstructor
public class AccountTransfer {
	private Long id;
	private String name;
	private Float balance;
	private String type;
	private Boolean confirmed;

	public void setId(Long id) {
		this.id = id;
	}

	public AccountTransfer(AccountEntity accountEntity) {
		this.setId(accountEntity.getId());
//		this.name = accountEntity.getName();
//		this.balance = accountEntity.getBalance();
//		this.type = accountEntity.getAccountType().toString();
//		this.confirmed = accountEntity.getConfirmed();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AccountTransfer that = (AccountTransfer) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
