package com.nazartsyhaniuk.dev.onlinebanking.service;

import com.nazartsyhaniuk.dev.onlinebanking.dto.TransactionDto;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Account;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Transaction;

import java.security.Principal;
import java.util.List;

public interface TransactionService {

    Transaction create(TransactionDto transactionDto, Principal principal);

    List<Transaction> findAll();

    List<Transaction> findByRecipientAccountNumber(String recipientAccountNumber);

    List<Transaction> findAllByAccount(Account account);

    List<Transaction> findByDate(String date);
}
