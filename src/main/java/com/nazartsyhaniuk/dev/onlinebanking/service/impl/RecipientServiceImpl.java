package com.nazartsyhaniuk.dev.onlinebanking.service.impl;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Account;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Recipient;
import com.nazartsyhaniuk.dev.onlinebanking.repository.RecipientRepository;
import com.nazartsyhaniuk.dev.onlinebanking.service.CustomerService;
import com.nazartsyhaniuk.dev.onlinebanking.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class RecipientServiceImpl implements RecipientService {

    private final RecipientRepository recipientRepository;
    private final CustomerService customerService;

    @Autowired
    public RecipientServiceImpl(RecipientRepository recipientRepository, CustomerService customerService) {
        this.recipientRepository = recipientRepository;
        this.customerService = customerService;
    }

    @Override
    public Recipient create(Recipient recipient, Principal principal) {
        recipient.setAccount(customerService.findByCISNumber(principal.getName()).getAccount());

        return recipientRepository.save(recipient);
    }

    @Override
    public List<Recipient> findAll() {
        return recipientRepository.findAll();
    }

    @Override
    public List<Recipient> findAllByAccount(Account account) {
        return recipientRepository.findAllByAccount(account);
    }

    @Override
    public void deleteRecipientById(long id) {
        recipientRepository.deleteById(id);
    }
}
