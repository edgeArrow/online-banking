package com.nazartsyhaniuk.dev.onlinebanking.dao.impl;

import com.nazartsyhaniuk.dev.onlinebanking.dao.CustomerDao;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private final EntityManager entityManager;

    @Autowired
    public CustomerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Customer customer) {
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
}
