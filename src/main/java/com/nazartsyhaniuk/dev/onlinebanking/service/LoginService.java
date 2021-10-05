package com.nazartsyhaniuk.dev.onlinebanking.service;

import com.nazartsyhaniuk.dev.onlinebanking.dto.CustomerLoginDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginService {

    ResponseEntity<Map<String, String>> signIn(CustomerLoginDto customerLoginDto);
}
