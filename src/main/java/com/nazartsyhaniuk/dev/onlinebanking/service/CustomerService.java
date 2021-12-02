package com.nazartsyhaniuk.dev.onlinebanking.service;

import com.nazartsyhaniuk.dev.onlinebanking.dto.CustomerDto;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;

import java.util.List;

public interface CustomerService {

    void create(CustomerDto customer);

    Customer findById(Long id);

    List<Customer> findAll();

    void delete(Long id);

    Customer findByMail(String mail);

    Customer findByCISNumber(String CISNumber);

    boolean checkCISNumber(String CISNumber);
}
