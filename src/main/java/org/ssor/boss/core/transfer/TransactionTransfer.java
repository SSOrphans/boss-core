package org.ssor.boss.core.transfer;

import lombok.*;
import org.ssor.boss.core.entity.Transaction;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
public class TransactionTransfer
{
  @NonNull
  private Integer id;
  private Float amount;
  private Float balance;
  private Float cashback;
  private String merchant;
  private LocalDateTime date;
  private String type;
  private Boolean pending;

  public TransactionTransfer(Transaction transaction)
  {
    this.id = transaction.getId();
    setEntity(transaction);
  }

  public void setEntity(Transaction transaction)
  {
    this.amount = transaction.getAmount();
    this.cashback = transaction.getCashback();
    this.balance = transaction.getNewBalance();
    this.merchant = transaction.getMerchantName();
    this.date = transaction.getDate();
    this.pending = transaction.getPending();
    this.type = transaction.getType().toString();
  }

}
