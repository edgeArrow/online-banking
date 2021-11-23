package com.nazartsyhaniuk.dev.onlinebanking.repository;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByRecipientAccountNumber(String recipientNumber);

    List<Transaction> findAllByDate(String date);
}
