package com.nazartsyhaniuk.dev.onlinebanking.service.impl;

import com.nazartsyhaniuk.dev.onlinebanking.dto.TransactionDto;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Account;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Transaction;
import com.nazartsyhaniuk.dev.onlinebanking.repository.TransactionRepository;
import com.nazartsyhaniuk.dev.onlinebanking.service.AccountService;
import com.nazartsyhaniuk.dev.onlinebanking.service.CustomerService;
import com.nazartsyhaniuk.dev.onlinebanking.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService, CustomerService customerService, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Transaction create(TransactionDto transactionDto, Principal principal) {
        Account account = customerService.findByCISNumber(principal.getName()).getAccount();
        Account accountOfRecipient = accountService.findByAccountNumber(transactionDto.getRecipientAccountNumber());

        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);

        transaction.setDate(getCurrentDate());
        transaction.setAccount(account);
        transactionRepository.save(transaction);

        accountOfRecipient.addTransactionToAccount(transaction);

        makeTransaction(account, accountOfRecipient, transaction.getAmount());

        return transaction;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> findByRecipientAccountNumber(String recipientAccountNumber) {
        return transactionRepository.findAllByRecipientAccountNumber(recipientAccountNumber);
    }

    @Override
    public List<Transaction> findAllByAccount(Account account) {
        return transactionRepository.findAllByAccount(account);
    }

    @Override
    public List<Transaction> findByDate(String date) {
        return transactionRepository.findAllByDate(date);
    }

    private String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }

    private void makeTransaction(Account account, Account accountOfRecipient, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            accountOfRecipient.setBalance(accountOfRecipient.getBalance() + amount);
        }
    }
}