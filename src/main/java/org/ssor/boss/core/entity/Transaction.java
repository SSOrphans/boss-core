package org.ssor.boss.core.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transaction", schema = "boss")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Transaction
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NonNull
  @Column(name = "id")
  private Integer id;

  @Column(name = "overdraft_id")
  private Integer overdraftId;
  @Column(name = "atm_transaction_id")
  private Integer atmTransactionId;
  @Column(name = "merchant_name")
  private String merchantName;
  @Column(name = "amount")
  private Float amount;
  @Column(name = "new_balance")
  private Float newBalance;
  @Column(name = "date")
  private LocalDateTime date;
  @Column(name = "succeeded")
  private Boolean succeeded;
  @Column(name = "pending")
  private Boolean pending;
  @Column(name = "account_id")
  private Integer accountId;

  @Enumerated
  @Column(name = "type_id")
  private TransactionType type;

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transaction that = (Transaction) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id);
  }
}
