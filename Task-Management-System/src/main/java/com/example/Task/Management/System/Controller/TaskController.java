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

    //This will fetch all the task which is present in db
    @GetMapping("/getTask")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }


    //This will fetch the task which has provided id
    @GetMapping("/getById/{id}")
    public Task getById(@PathVariable int id){
        return taskService.getTaskById(id).orElseThrow(() -> new EntityNotFoundException("Requested Task not found"));
    }


    //this will add new task to db
    @PostMapping("/addTask")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }


    //This will update the existing task
    @PutMapping("/updateTask/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task task,@PathVariable int id) throws Throwable {
       if(taskService.existById(id)){  //Conditions to check if the desired task present in the db
           Task task1=taskService.getTaskById(id).orElseThrow(() -> new EntityNotFoundException("Requested Task not found")); //if it present then store in task else throw the exception
           task1.setId(task.getId());
           task1.setTitle(task.getTitle()); //keepon update the data and save it to repository
           task1.setType(task.getType());
           task1.setDescription(task.getDescription());
           taskService.addTask(task1);
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
        if(taskService.existById(id)) {   //checks if task with desired id present or not
            taskService.delete(id);   //deletes it if it is present
            HashMap<String, String>message= new HashMap<>(); //this section is for displaying customised message
            message.put("message", id + " task removed");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }else {
            HashMap<String, String>message= new HashMap<>();
            message.put("message", id + " task not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
