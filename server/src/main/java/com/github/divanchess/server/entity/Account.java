package com.github.divanchess.server.entity;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="Accounts")
@Data
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String number;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card_id;
}
