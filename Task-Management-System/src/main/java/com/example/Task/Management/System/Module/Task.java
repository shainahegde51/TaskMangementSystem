package com.example.Task.Management.System.Module;

import jakarta.persistence.*;
import lombok.*;





@Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class Task {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="Id")
        private int id;

        private String title;

        private String type;

        private String description;


    }




