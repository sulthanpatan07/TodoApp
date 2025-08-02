package com.project.Todo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Todo.Model.User;
import com.project.Todo.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository urepo;
	
	public User registerUser(User user) {
		return urepo.save(user);
	}
	
	public User loginUser(String username,String password) {
		User existingUser=urepo.findByUsername(username);
		if(existingUser!=null && existingUser.getPassword().equals(password)) {
			return existingUser;
		}
		else {
			return null;
		}
	}
	
} 
