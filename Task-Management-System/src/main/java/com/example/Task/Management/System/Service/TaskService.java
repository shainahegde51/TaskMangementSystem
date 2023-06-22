package com.example.Task.Management.System.Service;
import java.util.List;
import java.util.Optional;

import com.example.Task.Management.System.Module.Task;
import com.example.Task.Management.System.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public boolean existById(int id) {
        return taskRepository.existsById(id);
    }

    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }
    public void delete(int id) {
        taskRepository.deleteById(id);
    }


}
