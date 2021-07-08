package org.ssor.boss.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "settings", schema = "boss")
public class Settings implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private AccountHolder accountHolder;

    @Column(name = "transaction_alerts")
    private int transactionAlerts;

    @Column(name = "balance_alerts")
    private int balanceAlerts;
}
