package com.example.Ecommerce.model;

import com.example.Ecommerce.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "cardNo", unique = true, nullable = false)
    String cardNo;

    @Column(name = "cvv")
    int cvv;

    @Column(name = "cardType")
    @Enumerated(EnumType.STRING)
    CardType cardType;

    @Column(name = "validTill")
    Date validTill;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
