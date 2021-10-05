package com.nazartsyhaniuk.dev.onlinebanking.service.impl;

import com.nazartsyhaniuk.dev.onlinebanking.dao.CustomerRepository;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import com.nazartsyhaniuk.dev.onlinebanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final Random random = new Random();

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void create(Customer customer) {
        customer.setCISNumber(createCISNumber());

        customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        Customer customer = null;

        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()){
            customer = optional.get();
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findByMail(String mail) {
        return customerRepository.findByMail(mail);
    }

    @Override
    public Customer findByCISNumber(String CISNumber) {
        return customerRepository.findByCISNumber(CISNumber);
    }


    private String createCISNumber() {
        StringBuilder CISNumber = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            CISNumber.append(random.nextInt(10));
        }

        return CISNumber.toString();
    }
}
