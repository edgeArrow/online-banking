package com.nazartsyhaniuk.dev.onlinebanking.service;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Account;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;

public interface AccountService {
    Account create();

    Account findById(Long id);

    Account findByAccountNumber(String accountNumber);

    boolean checkAccountNumberExist(String accountNumber);

    boolean delete(Customer admin);
}
