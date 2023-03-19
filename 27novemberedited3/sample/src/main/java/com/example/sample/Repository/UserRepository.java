package com.example.sample.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.Model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findUserByEmailAndPassword(String email,String password);
	public User findUserByEmail(String Email);

}
