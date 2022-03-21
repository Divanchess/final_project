package com.github.divanchess.server.entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Cards")
@Data
public class Card {

    @Id
    @GeneratedValue
    private Long id;

    private String pan;

    private int pin;

    @OneToMany(mappedBy = "card_id")
    private List<Account> accounts;
}
