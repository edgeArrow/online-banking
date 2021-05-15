package com.nazartsyhaniuk.dev.onlinebanking.entity;

import com.nazartsyhaniuk.dev.onlinebanking.validation.CheckEmail;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "surname")
    @NotBlank
    private String surname;

    @Column(name = "mail")
    @NotBlank
    @CheckEmail
    private String mail;

    @Column(name = "date_of_birth")
    @NotBlank
    private String dateOfBirth;

    @Column(name = "address")
    @NotBlank
    private String address;

    @Column(name = "city")
    @NotBlank
    private String city;

    @Column(name = "country")
    @NotBlank
    private String country;

    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{3}", message = "please use pattern XXX-XXX-XXX")
    @Column(name = "phone_number")
    @NotBlank
    private String phoneNumber;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "cis_number")
    private String CISNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<CreditCard> creditCards;

    public Customer() {
    }

    public Customer(String name, String surname, String dateOfBirth, String address, String city, String country,
                    String phoneNumber, String password, String CISNumber) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.CISNumber = CISNumber;
    }

    public void addCreditCardToCustomer(CreditCard creditCard) {
        if (creditCards == null) {
            creditCards = new ArrayList<>();
        }

        creditCards.add(creditCard);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCISNumber() {
        return CISNumber;
    }

    public void setCISNumber(String CISNumber) {
        this.CISNumber = CISNumber;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", CISNumber='" + CISNumber + '\'' +
                '}';
    }
}
