package com.nazartsyhaniuk.dev.onlinebanking.service;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Account;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;

public interface AccountService {
    Account create();

    Account findById(Long id);

    boolean delete(Customer admin);
}
