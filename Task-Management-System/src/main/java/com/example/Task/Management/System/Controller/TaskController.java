package com.example.Task.Management.System.Controller;

import com.example.Task.Management.System.Module.Task;
import com.example.Task.Management.System.Service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    //Adding task
    @GetMapping("/getTask")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }
    @GetMapping("/getById/{id}")
    public Task getById(@PathVariable int id){
        return taskService.getTaskById(id).orElseThrow(() -> new EntityNotFoundException("Requested Task not found"));
    }

    @PostMapping("/addTask")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task task,@PathVariable int id) throws Throwable {
       if(taskService.existById(id)){
            task=taskService.getTaskById(id).orElseThrow(() -> new EntityNotFoundException("Requested Task not found"));
           task.setTitle(task.getTitle());
           task.setType(task.getType());
           task.setDescription(task.getDescription());
           taskService.addTask(task);
           return ResponseEntity.ok().body(task);
       }
       else{
           HashMap<String,String>message=new HashMap<>();
           message.put("message", id +"task not found or matched");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
       }
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id) {
        if(taskService.existById(id)) {
            taskService.delete(id);
            HashMap<String, String>message= new HashMap<>();
            message.put("message", id + " task removed");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }else {
            HashMap<String, String>message= new HashMap<>();
            message.put("message", id + " task not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
