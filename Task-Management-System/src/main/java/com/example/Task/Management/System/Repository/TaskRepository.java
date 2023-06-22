package com.example.Task.Management.System.Repository;

import com.example.Task.Management.System.Module.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository  extends JpaRepository<Task,Long> {
}
