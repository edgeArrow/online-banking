package com.nazartsyhaniuk.dev.onlinebanking.dto;

import com.nazartsyhaniuk.dev.onlinebanking.dto.transfer.ExistData;
import com.nazartsyhaniuk.dev.onlinebanking.dto.transfer.NewData;
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

    @Null(groups = {NewData.class})
    private Long id;

    @NotBlank(groups = {NewData.class, ExistData.class},
            message = "Please provide your name")
    private String name;

    @NotBlank(groups = {NewData.class, ExistData.class},
            message = "Please provide your surname")
    private String surname;

    @CheckEmail
    @NotBlank(groups = {NewData.class, ExistData.class},
            message = "Please provide your mail")
    private String mail;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(groups = {NewData.class, ExistData.class},
            message = "Please provide your date of birth")
    private String dateOfBirth;

    @NotBlank(groups = {NewData.class, ExistData.class},
            message = "Please provide your address")
    private String address;

    @NotBlank(groups = {NewData.class, ExistData.class},
            message = "Please provide city where you birth")
    private String city;

    @NotBlank(groups = {NewData.class, ExistData.class},
            message = "Please provide country where you birth")
    private String country;

    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{3}", message = "Please use pattern XXX-XXX-XXX")
    @NotBlank(groups = {NewData.class, ExistData.class},
            message = "Please provide your number")
    private String phoneNumber;

    @NotBlank()
    private String password;
    private String matchingPassword;


}
