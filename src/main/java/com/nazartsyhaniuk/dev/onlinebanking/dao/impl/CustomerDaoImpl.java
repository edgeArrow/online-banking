package com.nazartsyhaniuk.dev.onlinebanking.dao.impl;

import com.nazartsyhaniuk.dev.onlinebanking.dao.CustomerDao;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Random;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private final EntityManager entityManager;
    private final Random random = new Random();

    @Autowired
    public CustomerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Customer customer) {
        customer.setCISNumber(createCISNumber());

        Customer newCustomer = entityManager.merge(customer);
        customer.setId(newCustomer.getId());
    }

    @Override
    public Customer findById(Long id) {

        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {

        return entityManager.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        Query query = entityManager.createQuery("delete from Customer where id =: customerId");

        query.setParameter("customerId", id);
        query.executeUpdate();
    }

    private String createCISNumber() {
        StringBuilder CIS = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            CIS.append(random.nextInt(10));
        }

        return CIS.toString();
    }
}
