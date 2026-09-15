package com.eazybytes.cards.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cards extends BaseEntity{

    @Column(name="card_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    @Column(name="mobile_number")
    private String mobileNumber;
    @Column(name="card_number")
    private String cardNumber;
    @Column(name="card_type")
    private String cardType;
    @Column(name="total_limit")
    private int totalLimit;
    @Column(name="amount_used")
    private int amountUsed;
    @Column(name="available_amount")
    private int availableAmount;
}
