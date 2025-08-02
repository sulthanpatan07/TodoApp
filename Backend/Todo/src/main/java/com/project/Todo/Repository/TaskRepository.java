package com.project.Todo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Todo.Model.Task;
import com.project.Todo.Model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByUser(User user);
}
