package com.nazartsyhaniuk.dev.onlinebanking.service;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;

import java.util.List;

public interface CustomerService {

    void create(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    void delete(Long id);
}
