package com.nazartsyhaniuk.dev.onlinebanking.entity;

import com.nazartsyhaniuk.dev.onlinebanking.dto_DEPRICATED.transfer.ExistData;
import com.nazartsyhaniuk.dev.onlinebanking.dto_DEPRICATED.transfer.NewData;
import com.nazartsyhaniuk.dev.onlinebanking.validation.mail.CheckEmail;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "mail")
    @CheckEmail
    @NotBlank(groups = {NewData.class, ExistData.class},
            message = "Please provide your mail")
    private String mail;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(groups = {NewData.class, ExistData.class},
            message = "Please provide your date of birth")
    private String dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{3}", message = "Please use pattern XXX-XXX-XXX")
    @NotBlank(groups = {NewData.class, ExistData.class},
            message = "Please provide your number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "cis_number")
    private String CISNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<CreditCard> creditCards;

    public void addCreditCardToCustomer(CreditCard creditCard) {
        if (creditCards == null) {
            creditCards = new ArrayList<>();
        }

        creditCards.add(creditCard);
    }
}
