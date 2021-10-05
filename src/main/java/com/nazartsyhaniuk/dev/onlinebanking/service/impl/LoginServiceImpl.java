package com.nazartsyhaniuk.dev.onlinebanking.service.impl;

import com.nazartsyhaniuk.dev.onlinebanking.dao.CustomerRepository;
import com.nazartsyhaniuk.dev.onlinebanking.dto.CustomerLoginDto;
import com.nazartsyhaniuk.dev.onlinebanking.entity.Customer;
import com.nazartsyhaniuk.dev.onlinebanking.security.JwtTokenUtil;
import com.nazartsyhaniuk.dev.onlinebanking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authManager;
    private final CustomerRepository customerRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public LoginServiceImpl(AuthenticationManager authManager, CustomerRepository customerRepository, JwtTokenUtil jwtTokenUtil) {
        this.authManager = authManager;
        this.customerRepository = customerRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public ResponseEntity<Map<String, String>> signIn(CustomerLoginDto customer) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(),
                    customer.getPassword()));

            Customer regCustomer = customerRepository.findByCISNumber(customer.getUsername());

            String token = jwtTokenUtil.createToken(customer.getUsername());

            String username = regCustomer.getName() + " " + regCustomer.getSurname();

            Map<String, String> model = new HashMap<>();
            model.put("token", token);
            model.put("username", username);
            model.put("role", regCustomer.getRole().toString());

            return ResponseEntity.ok(model);
        } catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Credentials aren't correct", exception);
        }
    }
}
