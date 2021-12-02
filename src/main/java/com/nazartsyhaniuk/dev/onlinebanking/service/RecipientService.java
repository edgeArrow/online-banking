package com.nazartsyhaniuk.dev.onlinebanking.service;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Account;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Recipient;

import java.security.Principal;
import java.util.List;

public interface RecipientService {

    Recipient create(Recipient recipient, Principal principal);

    List<Recipient> findAll();

    List<Recipient> findAllByAccount(Account account);

    void deleteRecipientById(long id);
}
