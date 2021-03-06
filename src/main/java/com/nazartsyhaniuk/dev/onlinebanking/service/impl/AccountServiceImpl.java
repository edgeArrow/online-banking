package com.nazartsyhaniuk.dev.onlinebanking.service.impl;

import com.nazartsyhaniuk.dev.onlinebanking.repository.AccountRepository;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Account;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import com.nazartsyhaniuk.dev.onlinebanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    private final Random random = new Random();

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create() {
        Account createdAccount = new Account();

        createdAccount.setBalance(100.0);
        createdAccount.setDateOfCreation(getCurrentDate());
        createdAccount.setAccountNumber(generateAccountNumber());

        accountRepository.save(createdAccount);

        return accountRepository.findByAccountNumber(createdAccount.getAccountNumber());
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public boolean delete(Customer admin) {
        return false;
    }

    private String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            accountNumber.append(random.nextInt(10));
        }

        return accountNumber.toString();
    }

    public boolean checkAccountNumberExist(String accountNumber) {
        return findByAccountNumber(accountNumber) != null;
    }

    private String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }
}
