package com.nazartsyhaniuk.dev.onlinebanking.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CustomerLoginDto {

    @NotBlank(message = "Please provide your mail")
    private String username;

    @NotBlank(message = "This field mustn't be empty. Please, enter your password")
    @Size(min = 8, max = 32)
    private String password;
}
