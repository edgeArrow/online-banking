package com.nazartsyhaniuk.dev.onlinebanking.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "recipients")
public class Recipient implements Serializable {
    private static final long serialVersionUID = 7L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name_of_recipient")
    @NotBlank(message = "This field must be filled")
    private String nameOfRecipient;

    @Column(name = "recipient_account_number")
    @NotBlank(message = "This field must be filled")
    @Size(min = 12, max = 12, message = "Account number must be 12 digits")
    private String recipientAccountNumber;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id")
    private Account account;
}
