package com.nazartsyhaniuk.dev.onlinebanking.service.impl;

import com.nazartsyhaniuk.dev.onlinebanking.repository.CustomerRepository;
import com.nazartsyhaniuk.dev.onlinebanking.dto.CustomerDto;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import com.nazartsyhaniuk.dev.onlinebanking.service.AccountService;
import com.nazartsyhaniuk.dev.onlinebanking.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountService accountService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final Random random = new Random();

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AccountService accountService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.accountService = accountService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void create(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setCISNumber(createCISNumber());
        customer.setAccount(accountService.create());

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

    public boolean checkCISNumber(String CISNumber) {
        return findByCISNumber(CISNumber) != null;
    }
}
