package com.youssef.workout_tracker.controller;

import com.youssef.workout_tracker.model.Exercise;
import com.youssef.workout_tracker.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Map<String, Object> body) {
        Exercise exercise = exerciseService.createExercise(
                Long.valueOf(body.get("routineId").toString()),
                body.get("name").toString(),
                Integer.parseInt(body.get("sets").toString()),
                Integer.parseInt(body.get("reps").toString())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(exercise);
    }

    @GetMapping("/routine/{routineId}")
    public ResponseEntity<List<Exercise>> getExercisesByRoutine(@PathVariable Long routineId) {
        return ResponseEntity.ok(exerciseService.getExercisesByRoutine(routineId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(
            @PathVariable Long id,
            @RequestBody Map<String, Object> body) {
        Exercise exercise = exerciseService.updateExercise(
                id,
                body.get("name").toString(),
                Integer.parseInt(body.get("sets").toString()),
                Integer.parseInt(body.get("reps").toString())
        );
        return ResponseEntity.ok(exercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}
