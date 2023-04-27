package com.poc.sap.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.poc.sap.model.Users; 
@Service
public interface UserService {
public Optional<Users> getUserDetail(int id);
public Users addUser(Users user);
}
