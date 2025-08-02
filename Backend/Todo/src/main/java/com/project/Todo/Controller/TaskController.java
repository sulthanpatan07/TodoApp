package com.project.Todo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Todo.Model.Task;
import com.project.Todo.Model.User;
import com.project.Todo.Repository.UserRepository;
import com.project.Todo.Service.TaskService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins ="*")
public class TaskController {
	
	@Autowired
	private TaskService tser;
	
	@Autowired
	private UserRepository userRepo;

	@PostMapping("/add")
    public Task addTask(@RequestBody Task task) {
        return tser.createTask(task);
    }
	
	@PostMapping("/add/{username}")
	public Task addTask(@PathVariable String username, @RequestBody Task taskData) {
	    User user = userRepo.findByUsername(username);
	    if (user != null) {
	        Task task = new Task();
	        task.setTitle(taskData.getTitle());
	        task.setDeadline(taskData.getDeadline());
	        task.setCompleted(false); // ✅ Always false on new task
	        task.setUser(user);       // ✅ Set user from path
	        return tser.createTask(task);
	    }
	    return null;
	}

	@GetMapping("/user/{username}")
	public List<Task> getTasksByUser(@PathVariable String username) {
	    User user = userRepo.findByUsername(username);
	    if (user != null) {
	        return tser.getTaskByUser(user);
	    }
	    return List.of(); // return empty list if user not found
	}

	
	 @PutMapping("/complete/{id}")
	    public Task completeTask(@PathVariable Long id) {
	        return tser.markTaskAsCompleted(id);
	    }
	 
	 @DeleteMapping("/delete/{id}")
	 public String deleteTask(@PathVariable Long id) {
	     return tser.deleteTask(id);
	 }

}
