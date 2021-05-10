package com.nazartsyhaniuk.dev.onlinebanking.entity;

import javax.persistence.*;

@Entity
@Table(name = "credit_cards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @Column(name = "expired_date")
    private String expiredDate;

    @Column(name = "balance")
    private long balance;

    public CreditCard() {
    }

    public CreditCard(String creditCardNumber, String expiredDate, long limit) {
        this.creditCardNumber = creditCardNumber;
        this.expiredDate = expiredDate;
        this.balance = limit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", expiredDate='" + expiredDate + '\'' +
                ", balance=" + balance +
                '}';
    }
}
