package com.nazartsyhaniuk.dev.onlinebanking.service.impl;

import com.nazartsyhaniuk.dev.onlinebanking.dao.CustomerDao;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import com.nazartsyhaniuk.dev.onlinebanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    @Transactional
    public void create(Customer customer) {
        customerDao.create(customer);
    }

    @Override
    @Transactional
    public Customer findById(Long id) {
        return customerDao.findById(id);
    }

    @Override
    @Transactional
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        customerDao.delete(id);
    }
}
