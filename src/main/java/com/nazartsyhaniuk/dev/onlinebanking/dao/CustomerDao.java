
package com.nazartsyhaniuk.dev.onlinebanking.dao;

import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerDao {

    void create(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    void delete(Long id);
}
