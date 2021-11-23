package com.nazartsyhaniuk.dev.onlinebanking.security;

import com.nazartsyhaniuk.dev.onlinebanking.repository.CustomerRepository;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customerDetailsService")
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByCISNumber(username);

        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }

        return new CustomerDetails(customer);
    }
}
