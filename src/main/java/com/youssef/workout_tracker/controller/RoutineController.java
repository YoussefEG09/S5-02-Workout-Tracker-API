package com.youssef.workout_tracker.controller;

import com.youssef.workout_tracker.model.Routine;
import com.youssef.workout_tracker.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @PostMapping
    public ResponseEntity<Routine> createRoutine(@RequestBody Map<String, String> body) {
        Routine routine = routineService.createRoutine(
                body.get("name"),
                body.get("description")
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(routine);
    }

    @GetMapping
    public ResponseEntity<List<Routine>> getMyRoutines() {
        return ResponseEntity.ok(routineService.getMyRoutines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Routine> getRoutineById(@PathVariable Long id) {
        return ResponseEntity.ok(routineService.getRoutineById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Routine> updateRoutine(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        Routine routine = routineService.updateRoutine(
                id,
                body.get("name"),
                body.get("description")
        );
        return ResponseEntity.ok(routine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return ResponseEntity.noContent().build();
    }
}
