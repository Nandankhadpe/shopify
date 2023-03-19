

package com.example.sample.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.Model.Category;
import com.example.sample.Model.Events;


public interface EventsRepository extends JpaRepository<Events, Integer>{
    
}
