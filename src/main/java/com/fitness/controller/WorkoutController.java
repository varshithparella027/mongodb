package com.fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.model.Workout;
import com.fitness.repository.WorkoutRepository;

@RestController
@RequestMapping("/api/workout")
@CrossOrigin("*")
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepo;

    @PostMapping("/add") 
    public String logWorkout(@RequestBody Workout workout) {
        workoutRepo.save(workout);
        return "Workout logged successfully";
    }

    @GetMapping("/history/{userId}")
    public List<Workout> getWorkoutHistory(@PathVariable String userId) {
        return workoutRepo.findByUserId(userId);
    }
    
}
