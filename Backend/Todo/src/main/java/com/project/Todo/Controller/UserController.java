package com.project.Todo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Todo.Model.User;
import com.project.Todo.Service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	private UserService userv;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		return userv.registerUser(user);
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User loginData) {
		String username=loginData.getUsername();
		String password=loginData.getPassword();
		return userv.loginUser(username, password);
	}

}
