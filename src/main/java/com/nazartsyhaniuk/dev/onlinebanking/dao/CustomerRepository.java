package com.nazartsyhaniuk.dev.onlinebanking.dao;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByMail(String mail);

    Customer findByCISNumber(String CISNumber);
}
