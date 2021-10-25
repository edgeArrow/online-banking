package com.nazartsyhaniuk.dev.onlinebanking.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name_of_recipient")
    private String nameOfRecipient;

    @Column(name = "amount")
    private double amount;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private String date;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id")
    private Account account;
}
