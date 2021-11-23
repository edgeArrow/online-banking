package com.nazartsyhaniuk.dev.onlinebanking.repository;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
}
