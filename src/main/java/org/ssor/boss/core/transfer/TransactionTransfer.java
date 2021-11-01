package org.ssor.boss.core.transfer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.ssor.boss.core.entity.TransactionEntity;
import java.time.LocalDateTime;

// TODO: fix this.
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
public class TransactionTransfer {
	private long id;
	private Float amount;
	private Float balance;
	private String merchant;
	private LocalDateTime date;
	private String type;
	private Boolean pending;

	public TransactionTransfer(TransactionEntity transactionEntity) {
		this.id = transactionEntity.getId();
		setEntity(transactionEntity);
	}

	public void setEntity(TransactionEntity transactionEntity) {
//		this.amount = transactionEntity.getAmount();
//		this.balance = transactionEntity.getNewBalance();
//		this.merchant = transactionEntity.getMerchantName();
//		this.date = transactionEntity.getDate();
//		this.pending = transactionEntity.getPending();
		this.type = transactionEntity.getType().toString();
	}
}
