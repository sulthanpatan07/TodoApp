package com.project.Todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Todo.Model.User;



public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
