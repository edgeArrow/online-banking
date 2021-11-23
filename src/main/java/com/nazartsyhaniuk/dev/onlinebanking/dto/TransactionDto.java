package com.nazartsyhaniuk.dev.onlinebanking.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TransactionDto {

    @NotBlank(message = "Please provide recipient's name")
    private String nameOfRecipient;

    @NotBlank(message = "Please provide recipient's account number")
    @Size(min = 12, max = 12)
    private String recipientAccountNumber;

    @NotNull(message = "Please provide amount of transaction")
    private double amount;

    @NotBlank(message = "Please provide title of transaction")
    private String title;
}
