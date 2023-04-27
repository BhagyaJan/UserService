package com.poc.sap.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.sap.model.Users;
import com.poc.sap.repo.UserRepo;
import com.poc.sap.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	public Optional<Users> getUserDetail(int id) {
		return userRepo.findById(id);
	}

	public Users addUser(Users user) {
		return userRepo.save(user);
		
	}

}
