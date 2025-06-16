package com.fitness.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("workouts") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workout {
    @Id
    private String id;
    private String userId;
    private String type;         
    private int duration;        
    private int caloriesBurned;
    private LocalDate date;     
}
