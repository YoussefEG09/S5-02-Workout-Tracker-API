package com.youssef.workout_tracker.controller;

import com.youssef.workout_tracker.model.Progress;
import com.youssef.workout_tracker.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping
    public ResponseEntity<Progress> createProgress(@RequestBody Map<String, Object> body) {
        LocalDate date = body.containsKey("date")
                ? LocalDate.parse(body.get("date").toString())
                : LocalDate.now();

        Progress progress = progressService.createProgress(
                Long.valueOf(body.get("exerciseId").toString()),
                date,
                body.get("note") != null ? body.get("note").toString() : null
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(progress);
    }

    @GetMapping("/exercise/{exerciseId}")
    public ResponseEntity<List<Progress>> getProgressByExercise(@PathVariable Long exerciseId) {
        return ResponseEntity.ok(progressService.getProgressByExercise(exerciseId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long id) {
        progressService.deleteProgress(id);
        return ResponseEntity.noContent().build();
    }
}
