package com.example.sample.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sample.Model.User;

import com.example.sample.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User>getAllUser(){
	    
	    return  userRepository.findAll();
	    }

	    public void addUser(User user){
	        
	        //if (userRepository.findByUserEmail(user.getUserEmail())==null)
	    	userRepository.save(user);
	    
	    
	    }
	    
	    public User isUser(String email,String password) {
	    	
	    	return userRepository.findUserByEmailAndPassword(email,password);
	    }
	    

}
