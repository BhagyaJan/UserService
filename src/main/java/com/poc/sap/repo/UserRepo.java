package com.poc.sap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.sap.model.Users;

@Repository
public interface UserRepo extends  JpaRepository<Users, Integer> {

}
