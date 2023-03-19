package com.example.sample.Repository;


import com.example.sample.Model.Coupons;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponsRepository extends JpaRepository<Coupons, Long>{
	List<Coupons>findAllByEventsId(int id);

	Coupons findByName(String name);


	
}
