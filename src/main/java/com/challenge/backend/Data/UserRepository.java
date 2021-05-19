package com.challenge.backend.Data;

import com.challenge.backend.Model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

    User findByUsername(String username);
    
}
