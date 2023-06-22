package com.example.Task.Management.System.Module;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class Task {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="Id")
        private Long id;

        private String title;

        private String type;

        private Date dueDate;

        private String description;
    }




