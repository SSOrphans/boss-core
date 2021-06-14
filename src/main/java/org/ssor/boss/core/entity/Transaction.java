package org.ssor.boss.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Describes the information of a transaction.
 *
 * @author Bermond Jon Batistiana
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "transaction", schema = "boss", uniqueConstraints = {
    @UniqueConstraint(columnNames = "id")
})
public class Transaction
{
  @Id
  @NonNull
  @Column(name = "id")
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Enumerated
  @Column(name = "type_id")
  private TransactionType type;
  @Column(name = "account_id")
  private Integer accountId;
  @Column(name = "overdraft_id")
  private Integer overdraftId;
  @Column(name = "atm_transaction_id")
  private Integer atmTransactionId;
  @Column(name = "merchant_name")
  private String merchantName;
  private Float amount;
  @Column(name = "new_balance")
  private Float newBalance;
  private LocalDateTime date;
  private Boolean succeeded;
  private Boolean pending;
}
