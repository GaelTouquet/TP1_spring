package com.example.todolist.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Todo {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date postDate;
    private boolean isDone;
    
}
