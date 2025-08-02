package com.project.Todo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Todo.Model.Task;
import com.project.Todo.Model.User;
import com.project.Todo.Repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository trepo;
	
	public Task createTask(Task task) {
		return trepo.save(task);
	}
	
	public List<Task> getTaskByUser(User user){
		return trepo.findByUser(user);
	}
	
	public Task markTaskAsCompleted(Long id) {
		Task task=trepo.findById(id).orElse(null);
		if(task!=null) {
			task.setCompleted(true);
			return trepo.save(task);
		}
		else {
			return null;
		}
	}
	
	public String deleteTask(Long id) {
	    trepo.deleteById(id);
	    return "Task deleted successfully!";
	}

}
