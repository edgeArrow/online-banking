package com.nazartsyhaniuk.dev.onlinebanking.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "accounts")
public class Account implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "balance")
    private double balance;

    @Column(name = "date_of_creation")
    private String dateOfCreation;

    @Column(name = "account_number")
    private String accountNumber;

    @OneToOne(mappedBy = "account", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Transaction> transactionsList;

    public void addTransactionToAccount(Transaction transaction) {
        if (transactionsList == null) {
            transactionsList = new ArrayList<>();
        }

        transactionsList.add(transaction);
    }
}
