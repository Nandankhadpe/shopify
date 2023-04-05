

package com.example.sample.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.Model.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
