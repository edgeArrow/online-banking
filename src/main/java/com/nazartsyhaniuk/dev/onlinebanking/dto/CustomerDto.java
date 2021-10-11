package com.nazartsyhaniuk.dev.onlinebanking.dto;

import com.nazartsyhaniuk.dev.onlinebanking.validation.mail.CheckEmail;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CustomerDto implements Serializable {

    @Null
    private Long id;

    @NotBlank(message = "Please provide your name")
    private String name;

    @NotBlank(message = "Please provide your surname")
    private String surname;

    @CheckEmail
    @NotBlank(message = "Please provide your mail")
    private String mail;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Please provide your date of birth")
    private String dateOfBirth;

    @NotBlank(message = "Please provide your address")
    private String address;

    @NotBlank(message = "Please provide city where you birth")
    private String city;

    @NotBlank(message = "Please provide country where you birth")
    private String country;

    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{3}", message = "Please use pattern XXX-XXX-XXX")
    @NotBlank(message = "Please provide your number")
    private String phoneNumber;

    @NotBlank(message = "Please provide your password")
    @Size(min = 6, max = 32)
    private String password;

}
