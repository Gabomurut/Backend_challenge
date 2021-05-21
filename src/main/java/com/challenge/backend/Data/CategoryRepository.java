package com.challenge.backend.Data;

import com.challenge.backend.Model.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> { 
    
}
